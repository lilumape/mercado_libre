/*
* Archivo: PronosticadorClimaHandlerTest
* Fecha: 1/09/2020
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
package co.com.mercadolibre.sistema.solar.componentes;

import co.com.mercadolibre.sistema.solar.entidades.Prediccion;
import co.com.mercadolibre.sistema.solar.modelos.ClimaOpciones;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

/**
 * Test del pronosticador del clima.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class PronosticadorClimaHandlerTest {

    @InjectMocks
    private PronosticadorClimaHandler pronosticadorHandler;

    @Description("Pronostica el clima un dia de lluvia")
    @Test

    public void testPronosticarDia() {
        int dia = 566;
        Prediccion condicionDia = pronosticadorHandler.getPronosticoDia(dia);
        Assertions.assertEquals(ClimaOpciones.LLUVIA.getId(), condicionDia.getClima().getIdClima());
    }

    @Description("Pronostica el clima un dia de sequia")
    @Test
    public void testPronosticarDiaCS() {
        int dia = 90;
        Prediccion condicionDia = pronosticadorHandler.getPronosticoDia(dia);
        Assertions.assertEquals(ClimaOpciones.SEQUIA.getId(), condicionDia.getClima().getIdClima());
    }

    @Description("Pronostica el clima un dia de condicion optima")
    @Test
    public void testPronosticarDiaCO() {
        int dia = 180;
        Prediccion condicionDia = pronosticadorHandler.getPronosticoDia(dia);
        Assertions.assertEquals(ClimaOpciones.SEQUIA.getId(), condicionDia.getClima().getIdClima());
    }

}
