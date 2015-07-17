package org.filterj.api.business.clauses;

import org.filterj.api.FilterWith;
import org.filterj.api.business.QueryType;

import java.lang.reflect.Field;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class BetweenClause extends Clause {

    private final FilterWith filterWith;

    public BetweenClause(Field beanField, QueryType queryType, boolean notFlag) {
        super(beanField, queryType, notFlag);
        filterWith = (FilterWith) beanField.getAnnotation(FilterWith.class);
    }

    public ClauseBean getClause() {
        String format = "(between (%s AND %s))";
        return new ClauseBean(String.format(format, getParamKey(), filterWith.paramKey()), getIgnoreValues());
    }

    protected final boolean isValid() {
        if (filterWith == null) {
            try {
                throw new Exception("FilterWith should use in combination with a Filter which has BETWEEN operator ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //TODO throwing exception for field name uncompatibility should happen at runtime
        //TODO when the program is binding value
        return false;
    }
}
