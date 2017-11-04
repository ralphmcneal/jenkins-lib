package vars

import org.junit.Before
import org.junit.Test

class HelloTest extends BasePipelineTestCase {
    @Before
    void setUp() throws Exception {
        super.setUp()
    }

    @Test
    void testScript() throws Exception {
        def script = loadScript("vars/hello.groovy")

        script.call("test")

        verify("sh", "echo test")
        verify("echo", "It Works, test.")
    }
}
