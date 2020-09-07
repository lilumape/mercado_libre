/*
* Archivo: ExcepcionHandler
* Fecha: 5/09/2020
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva de Mercado Libre.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito de Mercado Libre. quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
 */
package co.com.mercadolibre.sistema.solar.excepciones;

import co.com.mercadolibre.sistema.solar.modelos.ErrorResponse;
import co.com.mercadolibre.sistema.solar.utilidades.Constantes;
import co.com.mercadolibre.sistema.solar.utilidades.IdUtil;
import java.util.Date;
import java.util.Iterator;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manejador de excepciones.
 *
 * @author Mary Perez <lilumape@hotmail.com>
 */
@Slf4j
@ControllerAdvice
public class ExcepcionHandler extends ResponseEntityExceptionHandler {

    /**
     * Indica si se deben mostrar las excepciones en las respuesta de error.
     */
    @Value("${app.api.mostrarexcepciones}")
    boolean mostrarExcepciones;

    /**
     * Permite interceptar cualquier excepcion y generar un JSON con un codigo
     * de error generado el cual se puede buscar en el log para facilitar el
     * soporte y un mensaje generico por seguridad.
     *
     * @param e Exception
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> generalException(Exception e) {
        String errorCode = IdUtil.generate();
        String mensajeLog = String.format("%s - %s", errorCode, e.getMessage());
        setLog(mensajeLog, e);
        ErrorResponse error = new ErrorResponse();
        error.setErrorcode(errorCode);
        error.setTimestamp(new Date().toString());
        if (mostrarExcepciones) {
            error.setMessage(e.getMessage());
        } else {
            error.setMessage(Constantes.EXCEPTION_MESSAGE);
        }
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Permite interceptar excepciones de negocio y generar un JSON.
     *
     * @param e Exception
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler({SistemaRuntimeException.class})
    public ResponseEntity<ErrorResponse> negocioException(SistemaRuntimeException e) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorcode("1");
        error.setTimestamp(new Date().toString());
        error.setMessage(e.getExcepcion().name());
        return new ResponseEntity<>(error, e.getExcepcion().getStatus());
    }

    /**
     * Error de peticion.
     *
     * @param ex Excepcion generada
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            ConstraintViolationException ex) {
        Iterator<ConstraintViolation<?>> it = ex.getConstraintViolations().iterator();
        ConstraintViolation<?> c = it.next();
        String mensaje = String.format("%s %s", c.getInvalidValue(), c.getMessage());
        String errorCode = IdUtil.generate();
        ErrorResponse error = new ErrorResponse();
        error.setErrorcode(errorCode);
        error.setTimestamp(new Date().toString());
        error.setMessage(mensaje);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Escribe en el log del sistema.
     *
     * @param mensajeLog Mensaje del error
     * @param e Exception
     */
    void setLog(String mensajeLog, Exception e) {
        log.error(mensajeLog, e);
    }

}
