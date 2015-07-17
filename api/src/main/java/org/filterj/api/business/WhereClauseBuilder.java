package org.filterj.api.business;

import org.apache.log4j.Logger;
import org.filterj.api.Filter;
import org.filterj.api.Operator;
import org.filterj.api.business.clauses.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Map<Field, ClauseBean> getClause() {
        Field[] fields = clazz.getDeclaredFields();
        Annotation filterAnnotation;
        Map<Field, ClauseBean> mapFieldAndQuery = new HashMap<Field, ClauseBean>();

        for (Field classField : fields) {
            if ((filterAnnotation = classField.getAnnotation(Filter.class)) != null) {
                //TODO
                new Validations(classField).checkValidity();
                Filter filter = (Filter) filterAnnotation;
                mapFieldAndQuery.put(classField, getClauseBean(classField, filter.operator()));
            }
        }

        return mapFieldAndQuery;
    }


    private ClauseBean getClauseBean(Field classField, Operator operator) {
        ClauseBean clauseBean = null;
        switch (operator) {
            case EQUAL:
                clauseBean = new EqualClause(classField, QueryType.SQL, false).getClause();
                break;

            case NOT_EQUAL:
                clauseBean = new EqualClause(classField, QueryType.SQL, true).getClause();
                break;

            case IN:
                clauseBean = new InClause(classField, QueryType.SQL, false).getClause();
                break;
            case NOT_IN:
                clauseBean = new InClause(classField, QueryType.SQL, true).getClause();
                break;

            case BETWEEN:
                clauseBean = new BetweenClause(classField, QueryType.SQL, false).getClause();
                break;
            case NOT_BETWEEN:
                clauseBean = new BetweenClause(classField, QueryType.SQL, true).getClause();
                break;

            case GREATER:
                clauseBean = new GreaterClause(classField, QueryType.SQL).getClause();
                break;

            case LESSER:
                clauseBean = new LesserClause(classField, QueryType.SQL).getClause();
                break;

            case GREATER_EQUAL:
                clauseBean = new GreaterEqualClause(classField, QueryType.SQL).getClause();
                break;

            case LESS_EQUAL:
                clauseBean = new LessEqualClause(classField, QueryType.SQL).getClause();
                break;

            case LIKE:
                clauseBean = new LikeClause(classField, QueryType.SQL, false).getClause();
                break;
            case NOT_LIKE:
                clauseBean = new LikeClause(classField, QueryType.SQL, true).getClause();
                break;

            case IS_NULL:
                clauseBean = new NullClause(classField, QueryType.SQL, false).getClause();
                break;
            case IS_NOT_NULL:
                clauseBean = new NullClause(classField, QueryType.SQL, true).getClause();
                break;
        }
        return clauseBean;
    }

    private String createClause(List<Clause> clauses) {

        //StringBuilder clausesBuilder  = new StringBuilder(clauses.get(0).getClause());

//        for(int i = 1; i< clauses.size(); i++){
//           clausesBuilder.append(" AND " + clauses.get(i).getClause());
//        }

        return null;/*clausesBuilder.toString();*/

    }

    private String checkTableAnnotation(Field field) {
        Annotation filterAnnotation = null;

        try {
            Class tableClass = Class.forName("org.hibernate.annotations.Table");
            if ((filterAnnotation = field.getAnnotation(tableClass)) != null) {
                return String.valueOf(filterAnnotation.getClass().getMethod("tableName", null).invoke(String.class, null));
            } else {
                try {
                    throw new Exception("tabkle tableName not found");
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

    private synchronized static Logger log() {
        if (logger == null) {
            logger = Logger.getLogger(WhereClauseBuilder.class);
        }
        return logger;
    }
}
