
package com.mycompany.test.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;

/**
 * Class to generate assert statements for mapper test classes
 *
 * @author mohanarao_sv
 *
 */
public class GenerateAssertsUpdated {

    public static final String ASSERT_THAT = "assertThat(";

    public static final String IS_EQUAL_TO = ", is(equalTo(";

    public static final String CLOSED_BRACE = ")));";

    public static void main(final String[] args) {
        //  printAsserts(ArrayControlDetails.class, "arrayControl", "arrayControlType");
    }

    /**
     * Method to print assert statements
     *
     * @param clazz - model class to be asserted
     * @param sourceRefName - source model reference name
     * @param destRefName - destination model reference name
     */
    public static void printAsserts(final Class clazz, final String sourceRefName, final String destRefName) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && (method.getParameterTypes().length == 0) && (method.getReturnType() != void.class)
                    && (method.getName().startsWith("get") || method.getName().startsWith("is"))) {
                Class< ? > value = method.getReturnType();
                printAssertsBasedDataType(method, sourceRefName, destRefName);
                if (!value.getName().contains("Date") && (null != value.getSuperclass()) && value.getSuperclass().getName().contains("AbstractVo")) {
                    String className = value.getName().substring(value.getName().lastIndexOf(".") + 1);
                    System.out.println("//" + className);
                    printAsserts(value, getFieldName(method), getFieldName(method) + "Type");
                }
            }
        }

    }

    private static void printAssertsBasedDataType(final Method method, final String sourceRefName, final String destRefName) {
        Class< ? > value = method.getReturnType();
        if (!value.isEnum() && !value.getName().contains("Date")) {
            System.out.println(ASSERT_THAT + sourceRefName + "." + StringUtils.substring(method.toString(), method.toString().lastIndexOf(".") + 1) + IS_EQUAL_TO + destRefName
                    + "." + StringUtils.substring(method.toString(), method.toString().lastIndexOf(".") + 1) + CLOSED_BRACE);
        } else if (value.getName().contains("Date")) {
            System.out.println(ASSERT_THAT + sourceRefName + "." + StringUtils.substring(method.toString(), method.toString().lastIndexOf(".") + 1) + ".getSqlDateObject()"
                    + IS_EQUAL_TO + destRefName + "." + StringUtils.substring(method.toString(), method.toString().lastIndexOf(".") + 1) + CLOSED_BRACE);
        } else {
            System.out.println(ASSERT_THAT + sourceRefName + "." + StringUtils.substring(method.toString(), method.toString().lastIndexOf(".") + 1) + ".name()" + IS_EQUAL_TO
                    + destRefName + "." + StringUtils.substring(method.toString(), method.toString().lastIndexOf(".") + 1) + ".name()" + CLOSED_BRACE);
        }
    }

    /**
     * Method to get field name by passing get method
     *
     * @param method
     * @return
     */
    public static String getFieldName(final Method method) {
        try {
            Class< ? > clazz = method.getDeclaringClass();
            BeanInfo info = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : props) {
                if (method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod())) {
                    return pd.getName();
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
