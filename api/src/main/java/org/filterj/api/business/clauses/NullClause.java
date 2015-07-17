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

    public NullClause(Field beanField, QueryType queryType, boolean notFlag) {
        super(beanField, queryType, notFlag);
    }

    public ClauseBean getClause() {
        String format = "(%s IS%sNULL)";
        return new ClauseBean(String.format(format, getColumnName(), getFlag()), getIgnoreValues());
    }

    protected final boolean isValid() {
        return false;
    }
}
