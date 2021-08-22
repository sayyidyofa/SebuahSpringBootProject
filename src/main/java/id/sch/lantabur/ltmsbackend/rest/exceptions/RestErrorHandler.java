package id.sch.lantabur.ltmsbackend.rest.exceptions;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

// https://mflash.dev/blog/2020/07/26/error-handling-for-a-spring-based-rest-api/
@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleStatusException(ResponseStatusException ex, WebRequest request) {
        logger.error(ex.getReason(), ex);
        return handleResponseStatusException(ex, request);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error(ex.getLocalizedMessage(), ex);
        return handleEveryException(ex, request);
    }

    @SuppressWarnings("unchecked")
    protected @Override
    @NonNull ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex, Object body, @NonNull HttpHeaders headers,
                                                            HttpStatus status, @NonNull WebRequest request) {

        ResponseEntity<?> responseEntity;

        if (!status.isError()) {
            responseEntity = handleStatusException(ex, status, request);
        } else if (INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
            responseEntity = handleEveryException(ex, request);
        } else {
            responseEntity = handleEveryException(ex, request);
        }

        return (ResponseEntity<Object>) responseEntity;
    }

    protected ResponseEntity<RestErrorResponse> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        return RestErrorResponse.builder()
                .exception(ex)
                .path(getPath(request))
                .entity();
    }

    protected ResponseEntity<RestErrorResponse> handleStatusException(Exception ex, HttpStatus status, WebRequest request) {
        return RestErrorResponse.builder()
                .status(status)
                .message("Execution halted")
                .path(getPath(request))
                .entity();
    }

    protected ResponseEntity<RestErrorResponse> handleEveryException(Exception ex, WebRequest request) {
        return RestErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR)
                .message(ex.toString())
                .path(getPath(request))
                .entity();
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).substring(4);
    }
}
