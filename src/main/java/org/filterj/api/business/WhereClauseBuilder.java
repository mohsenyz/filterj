package org.filterj.api.business;

import org.apache.log4j.Logger;
import org.filterj.api.Filter;
import org.filterj.api.business.clauses.*;
import org.hibernate.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class WhereClauseBuilder {

    private Class<?> clazz;
    private static Logger logger = log();



    public WhereClauseBuilder(Class<?> clazz) {
        this.clazz = clazz;
    }

    public WhereClauseBuilder(String className) {
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getClause() {
        List<Clause> clauses = new ArrayList<Clause>();
        Field[] fields = clazz.getDeclaredFields();

        Annotation filterAnnotation;
        for (Field classField : fields) {
            if ((filterAnnotation = classField.getAnnotation(Filter.class)) != null) {
                new Validations(classField).checkValidity();

                Filter filter = (Filter)filterAnnotation;
                switch(filter.operator()){
                    case EQUAL:
                    case NOT_EQUAL:

                        clauses.add(new EqualClause("".equals(filter.name()) ? checkTableAnnotation(classField) : filter.name()));
                        break;

                    case IN:
                    case NOT_IN:
                        clauses.add(new InClause());
                        break;

                    case BETWEEN:
                    case NOT_BETWEEN:
                        clauses.add(new BetweenClause());
                        break;

                    case GREATER:
                        clauses.add(new GreaterClause());
                        break;

                    case LESSER:
                        clauses.add(new LesserClause());
                        break;

                    case GREATER_EQUAL:
                        clauses.add(new GreaterEqualClause());
                        break;

                    case LESS_EQUAL:
                        clauses.add(new LessEqualClause());
                        break;

                    case LIKE:
                    case NOT_LIKE:
                        clauses.add(new LikeClause());
                        break;

                    case IS_NULL:
                    case IS_NOT_NULL:
                        clauses.add(new NullClause());
                        break;
                }
            }
        }

        return createClause(clauses);
    }

    private String createClause(List<Clause> clauses){

        StringBuilder clausesBuilder  = new StringBuilder(clauses.get(0).getClause());

        for(int i = 1; i< clauses.size(); i++){
           clausesBuilder.append(" AND " + clauses.get(i).getClause());
        }

        return clausesBuilder.toString();

    }

    private synchronized static Logger log() {
        if (logger == null) {
            logger = Logger.getLogger(WhereClauseBuilder.class);
        }
        return logger;
    }

    private String checkTableAnnotation(Field field){
            Annotation filterAnnotation = null;

        try {
            Class tableClass = Class.forName("org.hibernate.annotations.Table");
            if ((filterAnnotation = field.getAnnotation(tableClass)) != null) {
                return String.valueOf(filterAnnotation.getClass().getMethod("name", null).invoke(String.class, null));
            }   else{
                try {
                    throw new Exception("tabkle name not found");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }
}
