package grails.interceptors.issue

import grails.converters.JSON

class ApiResponse {

    static MESSAGES = [
            200: 'OK',                  // The request was successful, we updated the resource and the response body contains the representation.
            201: 'Created',             // The request was successful, we created a new resource and the response body contains the representation.
            204: 'OK',                  // The request was successful; the resource was deleted.
            400: 'Bad Request',         // The data / params given in the POST or PUT failed validation. Inspect the response body for details.
            401: 'Unauthorized',        // The supplied credentials, if any, are not sufficient to access the resource.
            403: 'Bad Request',
            404: 'Not Found',
            405: 'Method Not Allowed',  // You cannot delete this resource
            409: 'Conflict',
            429: 'Too Many Requests',   // Your application is sending too many simultaneous requests.
            500: 'Internal Server Error',
            501: 'Not Implemented'
    ]

    static marshaller = {
        Map map = [
                code: it.code,
                data: it.data,
                status: it.status
        ]
        if (it.status in ['fail', 'error']) {
            map.message = it.message
        }
        return map
    }

    int code = 200
    def data = ''

    private String _message = ''

    String getStatus() {
        switch(code) {
            case 100..299:
                return 'success'
            case 400..499:
                return 'fail'
            case 500..599:
                return 'error'
        }
        return 'unknown'
    }

    String getMessage() {
        _message ?: MESSAGES[code]
    }

    void setMessage(String value) {
        _message = value
    }

    String toString() {
        "ApiResponse(status:$status, code:$code, message:$message, data:$data)"
    }

}
