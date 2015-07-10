package org.filterj.api.business.clauses;

import org.filterj.api.Filter;
import org.filterj.api.business.QueryType;

import java.lang.reflect.Field;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public abstract class Clause {

    protected QueryType queryType;
    private Field entityField;
    private Filter filter;

    public Clause(Field entityField, QueryType queryType) {
        this.entityField = entityField;
        this.filter = (Filter) entityField.getAnnotation(Filter.class);
        this.queryType = queryType;
    }

    public abstract ClauseBean getClause();

    public abstract boolean isValid();

    //TODO for example ? OR :tableName
    public String getParamKey() {
        return filter.paramKey();
    }

    protected String[] getIgnoreValues() {
        return filter.ignoreValues();
    }

    protected String getColumnName() {
        String columnName = filter.column();
        if ("".equals(columnName)) {
            columnName = entityField.getName();
        }
        return columnName;
    }
}
