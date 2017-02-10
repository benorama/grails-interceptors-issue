<!doctype html>
<html>
<head>
    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    <title>Error</title>
    <meta name="layout" content="main"/>
</head>
<body>

<g:if env="development">
    <h1 class="page-header">Grails Runtime Exception</h1>
    <g:if test="${Throwable.isInstance(exception)}">
        <g:renderException exception="${exception}" />
    </g:if>
    <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
        <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
    </g:elseif>
    <g:else>
        <ul class="errors">
            <li>An error has occurred</li>
            <li>Exception: ${exception}</li>
            <li>Message: ${message}</li>
            <li>Path: ${path}</li>
        </ul>
    </g:else>
</g:if>
<g:else>
    <h1 class="page-header">Oops, an error occurred...</h1>
    <p>
        We're sorry, but the application has encountered an error.<br />
        Please try again.
    </p>
</g:else>

</body>
</html>
