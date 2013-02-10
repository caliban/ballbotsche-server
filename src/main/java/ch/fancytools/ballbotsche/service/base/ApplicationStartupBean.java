/*
 * Copyright 2013 caliban.
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
package ch.fancytools.ballbotsche.service.base;

import ch.fancytools.ballbotsche.config.ConfigBean;
import ch.fancytools.ballbotsche.config.ConstConfig;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author caliban
 */
@Singleton
@Startup
@LocalBean
public class ApplicationStartupBean {

    private Logger logger;
    @Inject
    private ConfigBean configBean;

    @PostConstruct
    public void init() {
        try {
            initLog4j();
        } catch (IOException ioex) {
            throw new IllegalStateException(ioex);
        }
        verifyApplicationSetup();
    }

    private void initLog4j() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/META-INF/log4j.properties"));
        PropertyConfigurator.configure(props);
        logger = Logger.getLogger(ApplicationStartupBean.class);
        logger.info("success, log4j is going up");
    }

    private void verifyApplicationSetup() {
        Preconditions.checkNotNull(configBean, "wops, injection does NOT work, check for missing beans.xml");
        Preconditions.checkNotNull(configBean.getConfigForKey(ConstConfig.GAME_DAYS_KEY));
        Preconditions.checkNotNull(configBean.getConfigForKey(ConstConfig.MAX_NO_OF_PLAYERS_KEY));
        Preconditions.checkNotNull(configBean.getConfigForKey(ConstConfig.MIN_NO_OF_PLAYERS_KEY));
    }
}
