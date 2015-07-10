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

    Operator operator() default Operator.EQUAL;

    String column() default "";

    String paramKey() default "?";

    String regex() default "";

    int groupKey() default 0;

    String[] ignoreValues() default {};

}
