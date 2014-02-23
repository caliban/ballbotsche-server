/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.thp.proto.config;

import ch.thp.proto.config.domain.ConfigEntry;
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
    public ConfigEntry getConfigForKey(String key) {
        return em.createNamedQuery("configEntry.getByKey", ConfigEntry.class).setParameter("key", key)
                .getSingleResult();
    }
}