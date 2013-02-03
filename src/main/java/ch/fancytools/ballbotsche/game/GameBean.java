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
package ch.fancytools.ballbotsche.game;

import ch.fancytools.ballbotsche.config.ConfigValue;
import ch.fancytools.ballbotsche.config.ConstConfig;
import ch.fancytools.ballbotsche.domain.DaysOfWeek;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author caliban
 */
@Stateless
@LocalBean
public class GameBean {

    @PersistenceContext
    private EntityManager em;
    private final List<DaysOfWeek> possibleGameDays;
    
    @Inject
    public GameBean(List<DaysOfWeek> possibleGameDays)
    {
        this.possibleGameDays = possibleGameDays; 
    }
    
    
    public GameBean()
    {
        //=>http://stackoverflow.com/questions/9173592/can-i-use-cdi-constructor-injection-for-ejbs
        throw new IllegalStateException("use cdi constructor");
    }
    

    private Calendar calculateNextGameDate() {
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_WEEK, possibleGameDays.);
        return cal;
    }

    private boolean isTodayTuesday() {
        return Calendar.TUESDAY == Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public boolean isTodayMatch() {
        //todo
        return false;
    }
}
