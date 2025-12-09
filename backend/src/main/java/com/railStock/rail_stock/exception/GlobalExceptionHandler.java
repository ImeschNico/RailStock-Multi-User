package com.railStock.rail_stock.exception;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.railStock.rail_stock.dto.ErrorResponseDTO;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    /* -------------------------------------
     *  LOK-SPEZIFISCHE FEHLER
     * ------------------------------------- */

    @ExceptionHandler(LokNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleLokNotFound(
            LokNotFoundException ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "LOK_NOT_FOUND",
                "Lok mit ID " + ex.getArtNumber() + " wurde nicht gefunden.",
                404,
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateSerialNumberException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateSerial(
            DuplicateSerialNumberException ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "SERIAL_NUMBER_EXISTS",
                "Die Seriennummer '" + ex.getSerialNumber() + "' existiert bereits.",
                400,
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    /* -------------------------------------
     *  USER / AUTH / SECURITY
     * ------------------------------------- */

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthError(
            AuthenticationException ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "AUTHENTICATION_FAILED",
                "Authentifizierung fehlgeschlagen: " + ex.getMessage(),
                401,
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(
            AccessDeniedException ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "ACCESS_DENIED",
                "Zugriff verweigert. Sie besitzen nicht die erforderlichen Berechtigungen.",
                403,
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }


    /* -------------------------------------
     *  INVALID REQUESTS / VALIDATION
     * ------------------------------------- */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        StringBuilder message = new StringBuilder("Validierungsfehler: ");
        errors.forEach((field, msg) -> message.append(field).append(" - ").append(msg).append("; "));

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "VALIDATION_ERROR",
                message.toString(),
                400,
                extractPath(request)
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArg(
            IllegalArgumentException ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_ARGUMENT",
                ex.getMessage(),
                400,
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    /* -------------------------------------
     *  GENERISCHE FEHLER
     * ------------------------------------- */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(
            Exception ex, WebRequest request) {

        ex.printStackTrace(); // Für Debugging – nicht ans Frontend senden

        ErrorResponseDTO error = new ErrorResponseDTO(
                "INTERNAL_SERVER_ERROR",
                "Ein unerwarteter Fehler ist aufgetreten.",
                500,
                extractPath(request)
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /* -------------------------------------
     *  HELPER
     * ------------------------------------- */

    private String extractPath(WebRequest request){
        return request.getDescription(false).replace("uri=", "");
    }
}