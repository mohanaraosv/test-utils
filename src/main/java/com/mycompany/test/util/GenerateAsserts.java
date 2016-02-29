
package com.mycompany.test.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Class to generate assert statements for mapper test classes
 * 
 * @author mohanarao_sv
 *
 */
public class GenerateAsserts {

    public static final String ASSERT_THAT = "assertThat(";

    public static final String IS_EQUAL_TO = ", is(equalTo(";

    public static final String CLOSED_BRACE = ");";

    public static void main(final String[] args) {
        //  printAsserts(ListRoleProductResponse.class, "listRoleProductResponse", "listRoleProductResponseType");
    }

    /**
     * Method to print assert statements
     * 
     * @param clazz - model class to be asserted
     * @param sourceRefName - source model reference name
     * @param destRefName - destination model reference name
     */
    public static void printAsserts(final Class clazz, final String sourceRefName, final String destRefName) {
        List<Method> method = getMethods(clazz);
        for (Method method2 : method) {
            System.out.println(ASSERT_THAT + sourceRefName + StringUtils.substring(method2.toString(), method2.toString().lastIndexOf(".") + 1) + IS_EQUAL_TO + destRefName
                    + StringUtils.substring(method2.toString(), method2.toString().lastIndexOf(".") + 1) + CLOSED_BRACE);
        }
    }

    /**
     * Method to get all method defined in a class
     * 
     * @param clazz
     * @return list of methods
     */
    public static List<Method> getMethods(final Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        return Arrays.asList(methods);
    }
}
