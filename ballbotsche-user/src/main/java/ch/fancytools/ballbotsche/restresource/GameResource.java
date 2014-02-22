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
package ch.fancytools.ballbotsche.restresource;

import java.util.Calendar;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author caliban
 */
@Stateless
@Path("nextgame")
public class GameResource {

    private static final Logger LOGGER = Logger.getLogger(GameResource.class);

    
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String deliverRegisterPage()
//    {
//        try {
//            InputSupplier<? extends InputStream> supplier = new InputSupplier<InputStream>() {
//
//                @Override
//                public InputStream getInput() throws IOException {
//                   return getClass().getClassLoader().getResourceAsStream("nextgame.html");
//                }
//            };
//            return CharStreams.toString(CharStreams.newReaderSupplier(supplier, Charset.forName("UTF-8")));
//        } catch (IOException ex) {
//            java.util.logging.Logger.getLogger(GameResource.class.getName()).log(Level.SEVERE, null, ex);
//            return "failed, hard.";
//        }
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Calendar date)
    {
        
    }
}
