package org.filterj.api;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
@Filterable
public class Test {

    @Filter(column = "NAME", paramKey = ":name")
    private String name;

    @Filter(column = "F_NAME", paramKey = ":fName")
    private String familyName;
}
