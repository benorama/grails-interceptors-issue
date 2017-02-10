package grails.interceptors.issue

import grails.converters.JSON

class ErrorController {

    def badRequest() {
        params.message = params.message ?: 'Bad request'
        response.status = 400
        renderError('badRequest')
    }

    def unauthorized() {
        params.message = params.message ?: 'Unauthorized'
        response.status = 401
        renderError('unauthorized')
    }

    def forbidden() {
        response.status = 403
        renderError('forbidden')
    }

    def notFound() {
        params.message = params.message ?: 'Not found'
        response.status = 404
        renderError('notFound')
    }

    def notAllowed() {
        params.message = params.message ?: 'Not allowed'
        response.status = 401
        renderError('notAllowed')
    }

    def notImplemented() {
        params.message = params.message ?: 'Not implemented'
        response.status = 501
        renderError('notImplemented')
    }

    def error() {
        response.status = 500
        renderError('error')
    }

    def timeOut() {
        params.message = params.message ?: 'Time out'
        response.status = 500
        renderError('timeOut')
    }

     // PRIVATE

    private def renderError(String view) {
        withFormat {
            html { render view: "/error/${view}" }
            json { render new ApiResponse(code: response.status, message: params.message) as JSON }
        }
    }
    
}
