/*
* Archivo: ExcepcionHandlerTest
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
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test de manejador de excepciones.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class ExcepcionHandlerTest {

    /**
     * Clase a probar.
     */
    @Spy
    private ExcepcionHandler excepcionHandler;

    @DisplayName("Excepcion general")
    @Test
    public void generalException() {
        Mockito.doNothing().when(excepcionHandler).setLog(Mockito.anyString(), Mockito.any(Exception.class));
        RuntimeException e = new RuntimeException("Prueba");
        ResponseEntity<ErrorResponse> entity = excepcionHandler.generalException(e);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), entity.getStatusCodeValue());
    }

    @DisplayName("Excepcion de negocio")
    @Test
    public void negocioException() {
        SistemaRuntimeException e = new SistemaRuntimeException(ExcepcionOpciones.NO_EXISTEN_DATOS);
        ResponseEntity<ErrorResponse> entity = excepcionHandler.negocioException(e);
        Assertions.assertEquals(ExcepcionOpciones.NO_EXISTEN_DATOS.getStatus().value(), entity.getStatusCodeValue());
    }

    @DisplayName("Excepcion de bad request")
    @Test
    public void handleMethodArgumentNotValid() {

        ConstraintViolationSSImpl violation = new ConstraintViolationSSImpl();
        Set<ConstraintViolationSSImpl> violations = new HashSet<>();
        violations.add(violation);
        ConstraintViolationException ex = new ConstraintViolationException(violations);
        ResponseEntity<ErrorResponse> entity = excepcionHandler.handleMethodArgumentNotValid(ex);
        Assertions.assertEquals(entity.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
    }

    interface ConstraintViolationSS extends ConstraintViolation<String> {

    }

    public class ConstraintViolationSSImpl implements ConstraintViolationSS {

        @Override
        public String getMessage() {
            return "error";
        }

        @Override
        public String getMessageTemplate() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getRootBean() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Class<String> getRootBeanClass() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getLeafBean() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object[] getExecutableParameters() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getExecutableReturnValue() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Path getPropertyPath() {
            Path mockPath = mock(Path.class);
            return mockPath;
        }

        @Override
        public Object getInvalidValue() {
            return "";
        }

        @Override
        public ConstraintDescriptor<?> getConstraintDescriptor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <U> U unwrap(Class<U> type) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
