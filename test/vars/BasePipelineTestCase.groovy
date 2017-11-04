package vars

import com.lesfurets.jenkins.unit.BasePipelineTest;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasePipelineTestCase extends BasePipelineTest {
    void verify(String method, String args) {
        verify(1, method, args)
    }

    void verify(int count, String method, String args) {
        def all = helper.callStack.findAll { call ->
                call.methodName == method
        }

        assertTrue("Expected invocations to \'$method\' are incorrect. Wanted $count recieved ${all?.size()}", all?.size() == count)

        all.each { call ->
                assertEquals("Expected args are incorrect", args, call.argsToString())
        }
    }
}
