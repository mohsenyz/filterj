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

    public EqualClause(Field annotatedFilterField,QueryType queryType) {
        super(annotatedFilterField, queryType);
    }


    public ClauseBean getClause() {

        String clause = "";

        switch(queryType){
            case SQL:
                String format = "(%s = %s)";
                clause =  String.format(format, getColumnName(), getParamKey());
                break;

            case JPQL:
                break;
        }

        return new ClauseBean(clause, getIgnoreValues());
    }

    public boolean isValid() {
        return false;
    }
}
