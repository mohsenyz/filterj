package org.filterj.api.business.clauses;

import org.filterj.api.business.QueryType;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class EqualClause implements Clause {
    private String columnName;
    private QueryType queryType;

    //SQL constructor
    public EqualClause(String columnName) {
        this.columnName = columnName;
    }

    //JPQL constructor
    public EqualClause(String entityName, String parameterName){

    }

    public String getClause() {

        String clause = "";

        switch(queryType){
            case SQL:
                String format = "(%s = ?)";
                clause =  String.format(format, columnName);
                break;

            case JPQL:
                break;
        }

        return clause;
    }

    public boolean isValid() {
        return false;
    }
}
