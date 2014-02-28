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
package ch.thp.proto.time.mockdb;

import ch.thp.proto.time.user.domain.User;
import ch.thp.proto.time.user.domain.UserId;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Testsetup. poor mans database, aka a map in this case.
 *
 * @author Thierry
 */
@Singleton
@LocalBean
public class MockDatabase {

    private Map<String, User> users;

    @PostConstruct
    void init() {
        User userone = new User(new UserId(UUID.randomUUID().toString()), "ned.stark", "Eddard", "Stark");
        User usertwo = new User(new UserId(UUID.randomUUID().toString()), "john.snow", "John", "Snow");
        users = ImmutableMap.of(userone.getUsername(), userone, usertwo.getUsername(), usertwo);
    }

    public User getUserforUserName(String name) {
        return users.get(name);
    }

}
