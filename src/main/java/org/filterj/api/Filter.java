package org.filterj.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD}) //can use in method only.
public @interface Filter {

    public enum Operator {
        EQUAL, NOT_EQUAL, GREATER, LESSER, GREATER_EQUAL, LESS_EQUAL, BETWEEN, NOT_BETWEEN, LIKE, NOT_LIKE, IN, NOT_IN, IS_NULL, IS_NOT_NULL
    }

    public enum Condition {
        AND , OR
    }

    Operator operator() default Operator.EQUAL;
    String name() default "";
    String regex() default "";
    int groupUniqueKey() default 0;
    String[] ignoreValue() default "";

}
