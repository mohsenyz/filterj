package org.filterj.api;

import org.filterj.api.business.WhereClauseBuilder;
import org.filterj.api.business.clauses.ClauseBean;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
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

    private static volatile FilterAPI filterAPI = null;
    private static Map<Class<?>, Map<Field, ClauseBean>> map = null;

    private FilterAPI() {
        boot();
    }

    public static FilterAPI init() {
        if (filterAPI == null) {
            synchronized (FilterAPI.class) {
                if (filterAPI == null) {
                    filterAPI = new FilterAPI();
                }
            }
        }
        return filterAPI;
    }

    private static synchronized void boot() {
        map = new HashMap<Class<?>, Map<Field, ClauseBean>>();
        Reflections reflections = new Reflections("");
        Set<Class<?>> filterableAnnotated = reflections.getTypesAnnotatedWith(Filterable.class);

        for (Class<?> clazz : filterableAnnotated) {
            map.put(clazz, new WhereClauseBuilder(clazz).getClause());
        }
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

    public static String whereBuilder(Class<?> clazz) {
        Map<Field, ClauseBean> mapFields;
        StringBuilder stringBuilder = new StringBuilder();

        Set<Map.Entry<Field, ClauseBean>> filterMap;
        int temp = 0;

        for (Map.Entry<Class<?>, Map<Field, ClauseBean>> entry :  map.entrySet()) {
            if (clazz == entry.getKey()) {
                mapFields = entry.getValue();

                for (Map.Entry<Field, ClauseBean> entry1 : filterMap = mapFields.entrySet()) {
                    mapFields.entrySet();
                    temp++;
                    stringBuilder.append(entry1.getValue().getClause() + (filterMap.size() != temp ? " AND ":""));
                }
            }
        }
        return stringBuilder.toString();
    }
}
