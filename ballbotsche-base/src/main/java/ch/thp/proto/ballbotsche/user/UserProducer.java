/*
 * Copyright 2014 Thierry.
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
package ch.thp.proto.ballbotsche.user;

import ch.thp.proto.ballbotsche.user.CurrentUser;
import ch.thp.proto.ballbotsche.user.UserAccessBean;
import ch.thp.proto.ballbotsche.user.domain.User;
import com.google.common.base.Preconditions;
import java.security.Principal;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 * after some failed attempts like the usage of a filter or a jersey provider and 
 * learning quite a lot about bugs in the current glassfish I finally rembered
 * that the current principal is injectable. 
 * @author Thierry
 */
@RequestScoped
@Slf4j
public class UserProducer {

    @Inject
    private UserAccessBean userAccessBean;

    @Inject 
    private Principal currentPrincipal;
    
    @Produces
    @CurrentUser
    public User produceCurrentUser() {
        Preconditions.checkNotNull(currentPrincipal);
        log.debug("producing user .."+currentPrincipal.getName());
        return userAccessBean.getUserByUserName(currentPrincipal.getName());
    }
}
