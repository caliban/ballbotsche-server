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

import ch.fancytools.ballbotsche.domain.Player;
import com.google.common.base.Strings;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author caliban
 */
@Stateless
@Path("register")
public class RegisterResource {

    private static final Logger LOGGER = Logger.getLogger(RegisterResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void registerPlayer(Player spieler) {
        LOGGER.debug(spieler.getName());
    }

    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String deliverRegisterPage()
    {
        try {
            InputSupplier<? extends InputStream> supplier = new InputSupplier<InputStream>() {

                @Override
                public InputStream getInput() throws IOException {
                   return getClass().getClassLoader().getResourceAsStream("register.html");
                }
            };
            return CharStreams.toString(CharStreams.newReaderSupplier(supplier, Charset.forName("UTF-8")));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RegisterResource.class.getName()).log(Level.SEVERE, null, ex);
            return "failed, hard.";
        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{email}")
    public String getByEmail(@PathParam("email") String email) {
        LOGGER.debug(email);
        return email;
    }
}
