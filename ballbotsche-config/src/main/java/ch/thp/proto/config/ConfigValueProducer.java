/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.thp.proto.config;

import ch.thp.proto.config.domain.ConfigEntry;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;


/**
 *
 * @author Thierry
 */
@Dependent
public class ConfigValueProducer {
    
    @Inject
    private ConfigBean bean; 
    
    @Produces
    @ConfigValue("")
    public int produceIntValue(InjectionPoint point)
    {
        // zur verwirrung: feld "value" in annotation ConfigValue entspricht dem ConfigKey in der DB (Config Table)
        // value selber ist "builtin" dh. muss nicht mit Beispielsweise ConfigValue(key="x") annotiert werden sondern kann
        // mit ConfigValue("x") benutzt werden. 
        ConfigEntry conf = bean.getConfigForKey(point.getAnnotated().getAnnotation(ConfigValue.class).value());
        return Integer.valueOf(conf.getConfigValue());
    }
    
    @Produces
    @ConfigValue("")
    public String produceStringValue(InjectionPoint point)
    {
        //kommentar siehe oben. 
        return  bean.getConfigForKey(point.getAnnotated().getAnnotation(ConfigValue.class).value()).getConfigValue();
    }
    
}
