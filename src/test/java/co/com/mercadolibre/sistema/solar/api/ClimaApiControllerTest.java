/*
* Archivo: ClimaApiControllerTest
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
package co.com.mercadolibre.sistema.solar.api;

import co.com.mercadolibre.sistema.solar.entidades.Clima;
import co.com.mercadolibre.sistema.solar.modelos.ClimaResponse;
import co.com.mercadolibre.sistema.solar.servicios.PronosticadorService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test api.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class ClimaApiControllerTest {

    @InjectMocks
    private ClimaApiController climaApiController;
    @Mock
    private PronosticadorService pronosticadorService;

    @DisplayName("Respuesta Http 200")
    @Test
    public void http200() {
        mockServicio();
        ResponseEntity<?> res = climaApiController.dias("0");
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    private void mockServicio() {
        ClimaResponse clima = new ClimaResponse("1","SQUIA");
        Mockito.when(pronosticadorService.consultarDia(Mockito.anyLong())).thenReturn(clima);
    }

}
