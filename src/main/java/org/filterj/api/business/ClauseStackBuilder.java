package org.filterj.api.business;

import org.filterj.api.business.clauses.Clause;

import java.util.List;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/09
 * @since 1.0.0
 */
public class ClauseStackBuilder {

    private List<Clause> stackClause;

    public ClauseStackBuilder(List<Clause> stackClause){
        this.stackClause = stackClause;
    }

    public String getWhereClause(){
        return null;
    }
}
