/*
 * Copyright 2012 thierry.
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
package ch.fancytools.ballbotsche.config;

import ch.fancytools.ballbotsche.domain.Config;
import ch.fancytools.ballbotsche.domain.QConfig;
import com.mysema.query.jpa.impl.JPAQuery;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Configuration operations
 *
 * @author thierry
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ConfigBean {

    @PersistenceContext
    private EntityManager em;

    /**
     * gets a configuration depending on the key. every call will be made directly
     * to the database to prevent reading stale data from the cache.
     */
    public Config getConfigForKey(String key) {
        
        QConfig config = QConfig.config; 
        return new JPAQuery(em).from(config).where(config.configKey.eq(key)).uniqueResult(config);
        
        //old skool jpa :)
//        return em.createNamedQuery("getByKey", Config.class).setParameter("key", key)
//                .setHint("javax.persistence.cache.storeMode", "REFRESH").getSingleResult();
    }
}