package ralphmcneal.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before

import static junit.framework.TestCase.assertEquals
import static org.junit.Assert.assertTrue

public class PipelineSupport extends BasePipelineTest {
    @Before
    void setUp() throws Exception {
        super.setUp()
    }

    void verify(String method, String args) {
        verify(1, method, args)
    }

    void verify(int count, String method, String args) {
        def methodCalls = helper.callStack.findAll { call ->
                call.methodName == method
        }

        assertTrue("Expected invocations of \'$method\' are incorrect. Wanted $count but received ${methodCalls?.size()}", methodCalls?.size() == count)

        methodCalls.each { call ->
                assertEquals("Expected args are incorrect", args, call.argsToString())
        }
    }
}
