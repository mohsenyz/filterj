package org.filterj.api.business;

import java.lang.reflect.Field;

/**
 * @author Mehdi Afsari Kashi
 * @version 1.0.0
 *          <p/>
 *          Creation Date: 2015/07/08
 * @since 1.0.0
 */
public class Validations {

    private Field field;

    public Validations(Field field) {
        this.field = field;
    }

    private boolean isTableNameAccessible() {
        return true;
    }

    private boolean operationValidation() {
        //TODO a field with return type boolean can not operate with < > operators
        return true;
    }

    private boolean betweenOperator() {
        //TODO a field with between operator needs two party for comparison
        return true;
    }

    private boolean listOperator() {
        //TODO in (1, 2 ,3) needs list of values for example222
        return true;
    }

    private boolean inWrongList() {
        //TODO in should be performable to right list not for examlpe boolean
        return true;
    }

    public void checkValidity() {
        if (isTableNameAccessible() && operationValidation() && betweenOperator() && listOperator() && inWrongList()) {
            return;
        }
        System.exit(1);
    }
}
