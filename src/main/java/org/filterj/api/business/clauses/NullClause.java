package org.filterj.api.business.clauses;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/09
 * @since 1.0.0
 */
public class NullClause implements Clause {
    public String getClause() {
        return null;
    }

    public boolean isValid() {
        return false;
    }
}
