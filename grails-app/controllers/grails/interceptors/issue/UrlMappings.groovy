package grails.interceptors.issue

class UrlMappings {

    static mappings = {
        "/"(view: 'index') 
        "/admin/$action?"(controller: 'admin')
        "/foo/$action?"(controller: 'foo')
        "/monitor/$action?"(controller: 'monitor')  // Monitor pages application health (for Elastic Beanstalk)

        "/error/badRequest"(controller: 'error', action: 'badRequest')
        "/error/forbidden"(controller: 'error', action: 'forbidden')
        "/error/notFound"(controller: 'error', action: 'notFound')
        "/error/notAllowed"(controller: 'error', action: 'notAllowed')
        "/error/timeOut"(controller: 'error', action: 'timeOut')
        "/error/unauthorized"(controller: 'error', action: 'unauthorized')

        /* Does not work well, see https://github.com/grails/grails-core/issues/10391
        So we put default HTML views
        '400'(controller: 'error', action: 'badRequest')
        '401'(controller: 'error', action: 'unauthorized')
        '403'(controller: 'error', action: 'forbidden')
        '404'(controller: 'error', action: 'notFound')
        '405'(controller: 'error', action: 'notAllowed')
        '408'(controller: 'error', action: 'timeOut')
        '500'(controller: 'error', action: 'error')*/

        '400'(view: '/error/badRequest')
        '401'(view: '/error/unauthorized')
        '403'(view: '/error/forbidden')
        '404'(view: '/error/notFound')
        '405'(view: '/error/notAllowed')
        '408'(view: '/error/timeOut')
        '500'(view: '/error/error')

    }
}
