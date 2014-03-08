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

import ch.thp.proto.ballbotsche.user.domain.BallbotscheGroup;
import ch.thp.proto.ballbotsche.user.domain.BallbotscheUser;
import ch.thp.proto.ballbotsche.user.domain.UserId;
import com.google.common.collect.Sets;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Testsetup. poor mans database, aka a map in this case.
 *
 * @author Thierry
 */
@Singleton
@LocalBean
@Startup
public class TestDataLoader {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    void init() {
        BallbotscheGroup applicationUsers = new BallbotscheGroup(UUID.randomUUID().toString(), "ballbotscheUser", "can use the application");
        em.persist(applicationUsers);
        // http://approsto.com/sha-generator/
        //pw: test1
        BallbotscheUser userone = new BallbotscheUser(new UserId(UUID.randomUUID().toString()), "ned.stark", "G08OmFGXGZjnMgeFRMlrNsPQHO33yqMyNZ1vHYNWcBQ", Sets.newHashSet(applicationUsers));
        //pw: test1
        BallbotscheUser usertwo = new BallbotscheUser(new UserId(UUID.randomUUID().toString()), "john.snow", "G08OmFGXGZjnMgeFRMlrNsPQHO33yqMyNZ1vHYNWcBQ", Sets.newHashSet(applicationUsers));
        em.persist(userone);
        em.persist(usertwo);
    }
}
