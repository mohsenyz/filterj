package org.filterj.api.business.clauses;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/10
 * @since 1.0.0
 */
public class ClauseBean {



    private String clause;
    private String[] ignoreValues;

    public ClauseBean(String clause, String[] ignoreValues) {
        this.clause = clause;
        this.ignoreValues = ignoreValues;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String[] getIgnoreValues() {
        return ignoreValues;
    }

    public void setIgnoreValues(String[] ignoreValues) {
        this.ignoreValues = ignoreValues;
    }
}
