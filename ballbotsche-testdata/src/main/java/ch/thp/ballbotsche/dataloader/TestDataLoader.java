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
package ch.thp.ballbotsche.dataloader;

import ch.thp.proto.ballbotsche.user.domain.Group;
import ch.thp.proto.ballbotsche.user.domain.User;
import ch.thp.proto.ballbotsche.user.domain.UserId;
import com.google.common.collect.Sets;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Testsetup. poor mans database, aka a map in this case.
 *
 * @author Thierry
 */
@Singleton
@LocalBean
public class TestDataLoader {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    void init() {
        Group applicationUsers = new Group(UUID.randomUUID().toString(), "ballbotscheUser", "can use the application");
        em.persist(applicationUsers);
        //pw: test1
        User userone = new User(new UserId(UUID.randomUUID().toString()), "ned.stark", "1b4f0e9851971998e732078544c96b36c3d01cedf7caa332359d6f1d83567014", Sets.newHashSet(applicationUsers));
        //pw: test2
        User usertwo = new User(new UserId(UUID.randomUUID().toString()), "john.snow", "60303ae22b998861bce3b28f33eec1be758a213c86c93c076dbe9f558c11c752", Sets.newHashSet(applicationUsers));
        em.persist(userone);
        em.persist(usertwo);
    }
}
