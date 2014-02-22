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
package ch.thp.proto.time.resources.timestamp;

import ch.thp.proto.time.mockdb.MockDatabase;
import ch.thp.proto.time.user.CurrentUser;
import ch.thp.proto.time.user.domain.User;
import ch.thp.time.stamp.domain.TimesheetEntry;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Thierry
 */
@Path("timestamp")
public class TimeStampResource {

    @Inject
    private MockDatabase db;

    @Inject
    @CurrentUser
    private User current;

//    @GET
//    @Path("{tsid}")
//    @Produces("application/json")
//
//    public void getSingleTimeStamp(@PathParam("tsid") String tsid) {
////        UserId id = new User
//
//    }

    @GET
    @Produces("application/json")
    public List<TimesheetEntry> getAll() {
        return db.getTimeSheetEntries(current.getUserId());

    }
}
