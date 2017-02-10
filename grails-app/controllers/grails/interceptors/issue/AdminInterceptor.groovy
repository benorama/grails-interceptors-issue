package grails.interceptors.issue

import grails.core.GrailsApplication

class AdminInterceptor {

    GrailsApplication grailsApplication

    AdminInterceptor() {
        match(controller: 'admin')
        match(controller: 'console')
    }

    boolean before() {
        log.debug "Before ${actionName}..."
        // IP restriction for prod only
        if (!(InetAddressUtil.getAddress(request) in config?.allowedIps)) {
            log.error "User IP '${InetAddressUtil.getAddress(request)}' is not allowed to access $controllerName $actionName"
            redirect controller: 'error', action: 'notAllowed'
            return false
        }
        true
    }

    boolean after() {
        log.debug "After ${actionName}!"
        true
    }

    // PRIVATE

    def getConfig() {
        grailsApplication.config.grails?.interceptors?.issue?.admin
    }

}
