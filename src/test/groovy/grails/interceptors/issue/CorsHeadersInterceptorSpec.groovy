package grails.interceptors.issue


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CorsHeadersInterceptor)
class CorsHeadersInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test corsHeaders interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"corsHeaders")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
