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
public class LikeClause extends Clause {

    public LikeClause(Field beanField, QueryType queryType, boolean notFlag) {
        super(beanField, queryType, notFlag);
    }

    public ClauseBean getClause() {
        String format = "(%s%sLIKE %s)";
        return new ClauseBean(String.format(format, getColumnName(), getFlag(), getParamKey()), getIgnoreValues());
    }

    protected final boolean isValid() {
        return false;
    }
}
