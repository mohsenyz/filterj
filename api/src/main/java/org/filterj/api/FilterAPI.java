package org.filterj.api;

import org.filterj.api.business.WhereClauseBuilder;
import org.filterj.api.business.clauses.ClauseBean;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class FilterAPI {

    private static volatile FilterAPI filterAPI = new FilterAPI();
    private static Map<Class<?>, Map<Field, ClauseBean>> map = null;

    private static synchronized void boot() {

        Reflections reflections = new Reflections("");
        Set<Class<?>> filterableAnnotated = reflections.getTypesAnnotatedWith(Filterable.class);

        for(Class<?> clazz : filterableAnnotated){
            map.put(clazz,createFieldClauseMap(clazz));
        }
    }

    private static Map<Field, ClauseBean> createFieldClauseMap(Class<?> clazz) {
        return new WhereClauseBuilder(clazz).getClause();
    }

    public static synchronized FilterAPI init() {

        if (map == null) {
            map = new HashMap<Class<?>, Map<Field, ClauseBean>>();
            boot();
        }

        if (filterAPI == null) {
            filterAPI = new FilterAPI();
        }
        return filterAPI;
    }


    public static void print() {

        String entity;
        Map<Field, ClauseBean> mapFields;
        for (Map.Entry<Class<?>, Map<Field, ClauseBean>> entry : map.entrySet()) {
            entity = entry.getKey().getName();
            System.out.println(entity + "\n");
            System.out.println("-----------------------------\n");

            mapFields = entry.getValue();

            for (Map.Entry<Field, ClauseBean> entry1 : mapFields.entrySet()) {
                System.out.println(entry1.getKey().getName() + ":\t" + entry1.getValue().getClause() + "\n");
            }

            System.out.println("\n \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ \n");
        }
    }
}
