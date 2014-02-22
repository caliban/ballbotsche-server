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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author caliban
 */
@RequestScoped
public class GameDayProducer {

    private static final String SEPARATOR = ";";

    @Produces
    public List<DaysOfWeek> produceGameDaysOfWeek(@ConfigValue(ConstConfig.GAME_DAYS_KEY) String gameDaysAsKey) {
        String[] values = gameDaysAsKey.split(SEPARATOR);
        List<DaysOfWeek> retVal = new ArrayList<>();
        for (String val : values) {
            int dayOfWeek = Integer.valueOf(val);
            if (dayOfWeek > Calendar.SATURDAY) {
                throw new IllegalArgumentException("a week has still only 7 days");
            }
            retVal.add(DaysOfWeek.values()[dayOfWeek]);

        }
        return retVal;

    }
}
