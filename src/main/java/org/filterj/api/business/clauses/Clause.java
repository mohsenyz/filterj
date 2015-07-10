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
public abstract class Clause {

    protected QueryType queryType;
    private Field annotatedFilterField;
    private String columnName;
    private String [] ignoreValues;

    public Clause(Field annotatedFilterField, QueryType queryType){
        this.annotatedFilterField = annotatedFilterField;
        this.queryType = queryType;
    }

    public abstract ClauseBean getClause();

    public abstract boolean isValid();

    //TODO for example ? OR :name
    public String getParameterKeyName(){
        return null;
    }

    protected String[] getIgnoreValues() {
        return ignoreValues;
    }

    protected String getColumnName(){
        return null;
    }
}
