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
package ch.thp.proto.time.resources;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JsonParseExceptionMapper;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * see http://stackoverflow.com/questions/20709827/force-glassfish-4-to-use-jackson-2-3
 * @author Thierry
 */

@ApplicationPath("rest")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("ch.thp.proto.time.resources");
        register(GZipEncoder.class);
        register(new Feature() {
            public boolean configure(final FeatureContext context) {

                String postfix = '.' + context.getConfiguration().getRuntimeType().name().toLowerCase();

                context.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE + postfix, true);

                context.register(JsonParseExceptionMapper.class);
                context.register(JsonMappingExceptionMapper.class);
                context.register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
                
                return true;
            }
        }
        );
    }

}
