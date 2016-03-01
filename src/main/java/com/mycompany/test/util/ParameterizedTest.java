package com.edb.fs.enterprise.corews.soapui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edb.fs.enterprise.corews.common.test.AbstractTest;

@RunWith(Parameterized.class)
public class ParameterizedTest extends AbstractTest {

    protected final Logger    logger = LoggerFactory.getLogger(getClass());
    protected final TestModel testModel;

    @BeforeClass
    public static void beforeClass() {

    }

    public ParameterizedTest(final TestModel testModel) {
        this.testModel = testModel;
    }

    private static Collection<?> findFiles() {
        List<TestModel> testModels = new ArrayList<TestModel>();
        testModels.add(new TestModel("Sample-TestSuite1", "Sample-TestCase1", "Sample source application 1"));
        testModels.add(new TestModel("Sample-TestSuite2", "Sample-TestCase2", "Sample source application 2"));
        testModels.add(new TestModel("Sample-TestSuite3", "Sample-TestCase3", "Sample source application 3"));
        testModels.add(new TestModel("Sample-TestSuite4", "Sample-TestCase4", "Sample source application 4"));
        CollectionUtils.transform(testModels, new Transformer() {

            @Override
            public Object transform(final Object input) {
                return Collections.singletonList(input).toArray();
            }
        });
        return testModels;
    }

    @Parameters
    public static Collection<?> findProjects() {
        return findFiles();
    }

    @Test
    public void runProject() throws Exception {
        logger.info(testModel.getSourceApplication());
        logger.info(testModel.getTestCase());
        logger.info(testModel.getTestSuite());
    }

}
