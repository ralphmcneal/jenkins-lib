package vars

import org.junit.Before
import org.junit.Test

class HelloTest extends BasePipelineTestCase {
    @Before
    void setUp() throws Exception {
        super.setUp()
    }

    @Test
    void callScript() throws Exception {
        def script = loadScript("vars/hello.groovy")

        script.call("World")

        verify("echo", "Hello World!")
    }

    @Test
    void callScript_whenNoArgsPassed() throws Exception {
        def script = loadScript("vars/hello.groovy")

        script.call()

        verify("echo", "Hello There!")
    }
}
