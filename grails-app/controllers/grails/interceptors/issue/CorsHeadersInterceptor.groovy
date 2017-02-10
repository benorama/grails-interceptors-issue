package grails.interceptors.issue

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CorsHeadersInterceptor {

    CorsHeadersInterceptor() {
        match(uri: '/api/**')
    }

    boolean before() {
        Map grailsConfig = (Map) grailsApplication.config.grails
        String[] allowedOrigins = grailsConfig['cors']['allowedOrigins']?: null

        String origin = request.getHeader("Origin");
        boolean options = ("OPTIONS" == request.method)
        if (options) {
            safeAddHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS")
            if (origin != null) {
                safeAddHeader("Access-Control-Allow-Headers", "origin, authorization, accept, content-type, x-requested-with")
                safeAddHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS")
                safeAddHeader("Access-Control-Max-Age", "3600")
            }
        }

        if (allowedOrigins && allowedOrigins.contains(origin)) { // request origin is on the white list
            // add CORS access control headers for the given origin
            safeAddHeader("Access-Control-Allow-Origin", origin)
            safeAddHeader("Access-Control-Allow-Credentials", "true")
        } else if (!allowedOrigins) { // no origin white list
            // add CORS access control headers for all origins
            safeAddHeader("Access-Control-Allow-Origin", origin ?: "*")
            safeAddHeader("Access-Control-Allow-Credentials", "true")
        }

        !options // proceed to controller if method is not 'OPTIONS'
    }

    void safeAddHeader(String name, String value) {
        if (!response.containsHeader(name)) {
            header(name, value)
        } else {
            log.debug "Response contains header [${name}], value: [${value}]"
        }
    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }

}
