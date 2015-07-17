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

    public InClause(Field beanField, QueryType queryType, boolean notFlag) {
        super(beanField, queryType, notFlag);
    }

    public ClauseBean getClause() {
        String format = "(%s%sIN (%s))";
        return new ClauseBean(String.format(format, getColumnName(),getFlag(), "%s"), getIgnoreValues());
    }

    protected final boolean isValid() {
        return false;
    }
}
