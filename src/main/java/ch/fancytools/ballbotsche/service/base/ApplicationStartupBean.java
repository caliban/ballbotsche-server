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

import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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

    @PostConstruct
    public void init() {
        try {
            initLog4j();
        } catch (IOException ioex) {
            throw new IllegalStateException(ioex);
        }
    }

    private void initLog4j() throws IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/META-INF/log4j.properties"));
        PropertyConfigurator.configure(props);
        Logger.getLogger(ApplicationStartupBean.class).info("success, log4j is going up");
    }
}
