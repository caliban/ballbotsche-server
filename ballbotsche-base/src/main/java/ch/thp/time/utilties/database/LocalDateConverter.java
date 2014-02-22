/*
 * Copyright 2013 Thierry.
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
package ch.thp.time.utilties.database;

/**
 *
 * @author Thierry
 */
import java.sql.Date;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.LocalDate;

public class LocalDateConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : new LocalDate((Date) dataValue);
    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new LocalDate(((Date) objectValue).getTime());
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
