package org.filterj.api.business.clauses;

import org.filterj.api.business.QueryType;

import java.lang.reflect.Field;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/09
 * @since 1.0.0
 */
public class NullClause extends Clause {

    public NullClause(Field annotatedFilterField, QueryType queryType) {
        super(annotatedFilterField,queryType);
    }

    public ClauseBean getClause() {
        String format = "(%s > %s)";
        return new ClauseBean(String.format(format, getColumnName(), getParamKey()), getIgnoreValues());
    }

    public boolean isValid() {
        return false;
    }
}
