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
package ch.thp.proto.time.mockdb;

import ch.thp.proto.time.user.domain.User;
import ch.thp.proto.time.user.domain.UserId;
import ch.thp.time.stamp.domain.TimesheetEntry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 * Testsetup. poor mans database, aka a map in this case.
 *
 * @author Thierry
 */
@Singleton
@LocalBean
public class MockDatabase {

    private Map<String, User> users;
    private Map<UserId, List<TimesheetEntry>> entries;

    @PostConstruct
    void init() {
        User userone = new User(new UserId(UUID.randomUUID().toString()), "ned.stark", "Eddard", "Stark");
        User usertwo = new User(new UserId(UUID.randomUUID().toString()), "john.snow", "John", "Snow");
        TimesheetEntry entry0 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(230), LocalDate.parse("2014-01-01"), "did something");
        TimesheetEntry entry1 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(30), LocalDate.parse("2014-01-01"), "did more");
        TimesheetEntry entry2 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(120), LocalDate.parse("2014-01-01"), "did really some more");
        TimesheetEntry entry3 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(60), LocalDate.parse("2014-01-01"), "did nothing");
        TimesheetEntry entry4 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(20), LocalDate.parse("2014-01-01"), "did something again");
        TimesheetEntry entry5 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(15), LocalDate.parse("2014-01-02"), "did. did?");
        TimesheetEntry entry6 = new TimesheetEntry(UUID.randomUUID().toString(), userone.getUserId(), Duration.standardMinutes(125), LocalDate.parse("2014-01-02"), "yes, I did again something");
        TimesheetEntry entry25 = new TimesheetEntry(UUID.randomUUID().toString(), usertwo.getUserId(), Duration.standardMinutes(15), LocalDate.parse("2014-01-02"), "did. did?");
        TimesheetEntry entry26 = new TimesheetEntry(UUID.randomUUID().toString(), usertwo.getUserId(), Duration.standardMinutes(125), LocalDate.parse("2014-01-02"), "yes, I did again something");
        //can't tell why i have to cast again -_-
        entries = ImmutableMap.of(userone.getUserId(), (List<TimesheetEntry>) Lists.newArrayList(entry0, entry1, entry2, entry3, entry4, entry5, entry6),
                usertwo.getUserId(), (List<TimesheetEntry>) Lists.newArrayList(entry25, entry26));
        users = ImmutableMap.of(userone.getUsername(), userone, usertwo.getUsername(), usertwo);
    }

    public User getUserforUserName(String name) {
        return users.get(name);
    }
    public List<TimesheetEntry> getTimeSheetEntries(UserId id)
    {
        return entries.get(id);
    }
}
