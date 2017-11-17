package ralphmcneal.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.MethodCall
import org.junit.Before

import static junit.framework.TestCase.assertEquals
import static org.junit.Assert.assertTrue

class PipelineSupport extends BasePipelineTest {
    @Before
    void setUp() throws Exception {
        super.setUp()
    }

    void verify(String method, String args) {
        verify(1, method, args)
    }

    void verify(int count, String method, String... args) {
        def methodCalls = helper.callStack.findAll { call ->
            call.methodName == method
        }

        verifyCallCount(count, method, methodCalls)

        methodCalls.eachWithIndex { call, i ->
            assertEquals("Expected args do not match", (args.size() > 1 ? args[i] : args[0]), call.argsToString())
        }
    }

    void verify(String method, Closure<Boolean>... verifier) {
        verify(1, method, verifier)
    }

    void verify(int count, String method, Closure<Boolean>... verifier) {
        def methodCalls = helper.callStack.findAll { call ->
            call.methodName == method
        }

        verifyCallCount(count, method, methodCalls)

        methodCalls.eachWithIndex { call, i  ->
            assertTrue('Invocation $i of $method failed verification', (verifier.size() > 1 ? verifier[i](call.args) : verifier[0](call.args)))
        }
    }

    private verifyCallCount(int expectedCount, String method, List<MethodCall> methodCalls) {
        assertTrue("Expected invocations of \'$method\' are incorrect. Wanted $expectedCount but received ${methodCalls?.size()}", methodCalls?.size() == expectedCount)
    }
}
