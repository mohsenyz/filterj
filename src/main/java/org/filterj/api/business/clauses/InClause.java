package org.filterj.api.business.clauses;

import org.filterj.api.business.QueryType;

import java.lang.reflect.Field;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class InClause extends Clause {

    public InClause(Field annotatedFilterField, QueryType queryType) {
        super(annotatedFilterField,queryType);
    }

    public ClauseBean getClause() {
        return null;
    }

    public boolean isValid() {
        return false;
    }
}
