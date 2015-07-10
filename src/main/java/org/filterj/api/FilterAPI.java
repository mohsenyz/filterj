package org.filterj.api;

import org.filterj.api.business.WhereClauseBuilder;
import org.filterj.api.business.clauses.ClauseBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class FilterAPI {

    private static volatile FilterAPI filterAPI = new FilterAPI();
    private static Map<Class<?>, String> map = boot();

    private static synchronized Map boot() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Filterable.class, true));

        for (BeanDefinition bd : scanner.findCandidateComponents("")) {
            try {
                startTransaction(Class.forName(bd.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return map = new HashMap<Class<?>, String>();
    }

    public static synchronized FilterAPI init() {
        if (map == null) {
            boot();
        }

        if (filterAPI == null) {
            filterAPI = new FilterAPI();
        }
        return filterAPI;
    }


    private static String startTransaction(Class<?> clazz) {
        Map<Class<?>, Map<Field, ClauseBean>> map = new WhereClauseBuilder(clazz).getClause();
        return "salam";
    }
}
