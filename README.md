# Grails Interceptors issue

## Config

Grails **3.2.4**

## Description

This is an example app to try to reproduce the issue describe here:
https://github.com/grails/grails-core/issues/10458

The app has the following `AdminInterceptor` that secure `admin` and `console` controllers.

```groovy
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
```

On our app, under heavy load, this interceptor is wrongly executed and we receive errors in our log for controllers other than `admin` and `console`.