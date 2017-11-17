package vars

import org.junit.Before
import org.junit.Test
import ralphmcneal.jenkins.PipelineSupport

class HelloTest extends PipelineSupport {
    def scriptUnderTest

    @Before
    void setup() {
        scriptUnderTest = loadScript("vars/hello.groovy")
    }

    @Test
    void call() throws Exception {

        scriptUnderTest.call("World")

        verify("echo", "Hello World!")
    }

    @Test
    void call_whenNoArgsPassed() throws Exception {

        scriptUnderTest.call()

        verify("echo", "Hello There!")
    }
}
