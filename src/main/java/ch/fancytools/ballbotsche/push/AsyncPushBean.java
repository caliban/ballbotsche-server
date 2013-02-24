/*
 * Copyright 2013 caleb.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.fancytools.ballbotsche.push;

import ch.fancytools.ballbotsche.domain.Player;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author caleb
 */
@Stateful
@LocalBean
public class AsyncPushBean {

    private final Instance<Push2Mobile> pushers;

    public AsyncPushBean() {
        throw new IllegalArgumentException("do not call, use cdi constructor");
    }

    @Inject
    public AsyncPushBean(Instance<Push2Mobile> pushers) {
        this.pushers = pushers;
    }

    public void observe(@Observes EnoughPlayerEvent event) {
        for (final Push2Mobile pusher : pushers) {
            executePushersAsync(pusher, Lists.newArrayList(Iterables.filter(event.getGame().getPlayerList(), new Predicate<Player>() {
                @Override
                public boolean apply(Player player) {
                    return pusher.acceptPushNotficationForType(player.getDevice());
                }
            })));
        }
    }

    @Asynchronous
    public void executePushersAsync(Push2Mobile pusher, List<Player> sortedPlayers) {
        pusher.push(sortedPlayers);
    }
}
