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

    public InClause(Field beanField, QueryType queryType) {
        super(beanField, queryType);
    }

    public ClauseBean getClause() {
        String format = "(%s IN (%s))";
        return new ClauseBean(String.format(format, getColumnName(), "%s"), getIgnoreValues());
    }

    protected final boolean isValid() {
        return false;
    }
}
