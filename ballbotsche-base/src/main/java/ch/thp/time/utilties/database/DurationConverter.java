/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.thp.time.utilties.database;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.Duration;

/**
 * c/p from: https://code.google.com/p/joda-time-eclipselink-integration/
 * 
 * Persists org.joda.time.Duration using EclipseLink. Value is stored as a
 * varchar.
 *
 * @author georgi.knox
 *
 */
public class DurationConverter implements Converter {

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        if (dataValue instanceof String) {
            return new Duration(dataValue);
        }
        throw new IllegalStateException("Converstion exception, value is not of LocalDate type.");

    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session arg1) {

        if (objectValue instanceof Duration) {
            return ((Duration) objectValue).toString();
        }
        throw new IllegalStateException("Converstion exception, value is not of java.util.Date type.");
    }

    @Override
    public void initialize(DatabaseMapping arg0, Session arg1) {

    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
