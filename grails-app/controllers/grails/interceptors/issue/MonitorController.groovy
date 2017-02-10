package grails.interceptors.issue

import grails.converters.JSON
import grails.core.GrailsApplication

class MonitorController {

    GrailsApplication grailsApplication

    def index() {
        log.debug "Monitoring..."
        withFormat {
            html [:]
            json {
                Map monitor = [
                        message: "It works!",
                        version: grailsApplication?.metadata?.applicationVersion
                ]
                render monitor as JSON
            }
        }
    }

}
