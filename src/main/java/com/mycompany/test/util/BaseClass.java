/**
 * 
 */

package com.mycompany.test.util;

/**
 * @author e210636
 *
 */
public class BaseClass extends SuperClass {

    @Override
    public int getValue() {
        return 200;
    }

    public static void main(final String[] args) {
        BaseClass bc = new BaseClass();
        System.out.println(bc.doCalculation());
    }
}
