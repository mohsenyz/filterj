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
public class EqualClause extends Clause {

    public EqualClause(Field beanField, QueryType queryType, boolean notFlag) {
        super(beanField, queryType, notFlag);
    }


    public ClauseBean getClause() {

        String clause = "";

        switch (queryType) {
            case SQL:
                String format = "(%s %s %s)";
                clause = String.format(format, getColumnName(), isNotFlag() ? "<>" : "=", getParamKey());
                break;

            case JPQL:
                break;
        }

        return new ClauseBean(clause, getIgnoreValues());
    }

    protected final boolean isValid() {
        return false;
    }
}
