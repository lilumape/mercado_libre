/*
* Archivo: RectaUtilTest
* Fecha: 31/08/2020
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
package co.com.mercadolibre.sistema.solar.utilidades;

import co.com.mercadolibre.sistema.solar.modelos.Coordenada;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test unitario de la clase RectaUtil.
 *
 * @author Mary Perez <lperez@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class RectaUtilTest {

    @DisplayName("Valida la pendiente de una recta")
    @Test
    public void testPendiente() {
        Coordenada p1 = new Coordenada(BigDecimal.valueOf(5), BigDecimal.valueOf(2));
        Coordenada p2 = new Coordenada(BigDecimal.valueOf(3), BigDecimal.valueOf(4));
        //(y2-y1)/(x2-x1)
        BigDecimal pendienteEsperada = BigDecimal.valueOf(-1);
        BigDecimal pendienteObtenida = RectaUtil.getPendiente(p1, p2);
        Assertions.assertEquals(pendienteEsperada.doubleValue(), pendienteObtenida.doubleValue());
    }

    @DisplayName("Valida la pendiente de una recta cuando es 0")
    @Test
    public void testPendienteCero() {
        Coordenada p1 = new Coordenada(BigDecimal.valueOf(0), BigDecimal.valueOf(2));
        Coordenada p2 = new Coordenada(BigDecimal.valueOf(0), BigDecimal.valueOf(4));
        //(y2-y1)/(x2-x1)
        BigDecimal pendienteEsperada = BigDecimal.valueOf(0);
        BigDecimal pendienteObtenida = RectaUtil.getPendiente(p1, p2);
        Assertions.assertEquals(pendienteEsperada.doubleValue(), pendienteObtenida.doubleValue());
    }

    @DisplayName("Valida el punto de corte de una recta")
    @Test
    public void testPuntoCorte() {
        Coordenada p1 = new Coordenada(BigDecimal.valueOf(5), BigDecimal.valueOf(2));
        BigDecimal pendiente = BigDecimal.valueOf(-1);
        //y=mx+b
        BigDecimal puntoCorteEsperado = BigDecimal.valueOf(7);
        BigDecimal puntoCorteObtenido = RectaUtil.getPuntoCorte(p1, pendiente);
        Assertions.assertEquals(puntoCorteEsperado, puntoCorteObtenido);
    }

    @DisplayName("Valida si un punto esta contenido en una recta")
    @Test
    public void testPuntoContenidoEnRecta() {
        Coordenada p1 = new Coordenada(BigDecimal.valueOf(3), BigDecimal.valueOf(4));
        BigDecimal pendiente = BigDecimal.valueOf(-1);
        BigDecimal puntoCorte = BigDecimal.valueOf(7);
        boolean puntoContenido = RectaUtil.puntoContenidoEnRecta(p1, pendiente, puntoCorte);
        Assertions.assertEquals(true, puntoContenido);
    }

}
