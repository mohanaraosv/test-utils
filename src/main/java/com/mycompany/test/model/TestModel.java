package com.mycompany.test.model;

/**
 * @author mohanarao_sv
 *
 */
public class TestModel {

    private String testSuite;
    private String testCase;
    private String sourceApplication;

    /**
     * @param testSuite
     * @param testCase
     * @param sourceApplication
     */
    public TestModel(final String testSuite, final String testCase, final String sourceApplication) {
        this.testSuite = testSuite;
        this.testCase = testCase;
        this.sourceApplication = sourceApplication;
    }

    public String getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(final String testSuite) {
        this.testSuite = testSuite;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(final String testCase) {
        this.testCase = testCase;
    }

    public String getSourceApplication() {
        return sourceApplication;
    }

    public void setSourceApplication(final String sourceApplication) {
        this.sourceApplication = sourceApplication;
    }
}
