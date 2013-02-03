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

import ch.fancytools.ballbotsche.game.GameDayProducer;
import ch.fancytools.ballbotsche.domain.DaysOfWeek;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caliban
 */
public class GameDayProducerTest {

    @Test
    public void testOne() {
        GameDayProducer producer = new GameDayProducer();
        assertArrayEquals(new DaysOfWeek[]{DaysOfWeek.SUNDAY, DaysOfWeek.MONDAY},
                producer.produceGameDaysOfWeek("1;2").toArray(new DaysOfWeek[2]));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEightDay() {
        GameDayProducer producer = new GameDayProducer();
        producer.produceGameDaysOfWeek("1;8").toArray(new DaysOfWeek[2]);
    }

    @Test(expected = NumberFormatException.class)
    public void testDayAsString() {
        GameDayProducer producer = new GameDayProducer();
        producer.produceGameDaysOfWeek("1;MONDAY").toArray(new DaysOfWeek[2]);
    }
}
