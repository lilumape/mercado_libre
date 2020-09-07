/*
* Archivo: CoordenadaUtilTest
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
import java.math.RoundingMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

/**
 * Test unitario de la clase CoordenadaUtil.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class CoordenadaUtilTest {

    public CoordenadaUtilTest() {
    }

    @DisplayName("Convierte una coordenada polar en rectangular con un angulo menor a 90 grados")
    @Test
    public void testConvertirAnguloPequeno() {
        int angulo = 210;
        long distancia = 100;
        Coordenada coordenadaEsperada = new Coordenada(BigDecimal.valueOf(-50.00000000000004), BigDecimal.valueOf(-86.60254037844383));
        Coordenada coordenadaObtenida = CoordenadaUtil.convertirARectangular(angulo, distancia);
        BigDecimal xEsperado = coordenadaEsperada.getX().setScale(Constantes.PRECISION, RoundingMode.HALF_EVEN);
        BigDecimal yEsperado = coordenadaEsperada.getY().setScale(Constantes.PRECISION, RoundingMode.HALF_EVEN);
        BigDecimal xObtenido = coordenadaObtenida.getX().setScale(Constantes.PRECISION, RoundingMode.HALF_EVEN);
        BigDecimal yObtenido = coordenadaObtenida.getY().setScale(Constantes.PRECISION, RoundingMode.HALF_EVEN);
        Assertions.assertEquals(xEsperado, xObtenido);
        Assertions.assertEquals(yEsperado, yObtenido);
    }

    @DisplayName("Convierte una coordenada polar en rectangular con un angulo superior a 90 grados")
    @Test
    public void testConvertirAnguloGrande() {
        int angulo = 180;
        long distancia = 500;
        Coordenada coordenadaEsperada = new Coordenada(BigDecimal.valueOf(-9.184850993605149E-14), BigDecimal.valueOf(-500.0));
        Coordenada coordenadaObtenida = CoordenadaUtil.convertirARectangular(angulo, distancia);
        Assertions.assertEquals(coordenadaEsperada.getX(), coordenadaObtenida.getX());
        Assertions.assertEquals(coordenadaEsperada.getY(), coordenadaObtenida.getY());
    }

    @DisplayName("Convierte una coordenada polar en rectangular con un angulo que rota contrario a las manecillas del reloj")
    @Test
    public void testConvertirAnguloGiroOpuesto() {
        int angulo = -310;
        long distancia = 100;
        Coordenada coordenadaEsperada = new Coordenada(BigDecimal.valueOf(76.60444431189781), BigDecimal.valueOf(64.27876096865391));
        Coordenada coordenadaObtenida = CoordenadaUtil.convertirARectangular(angulo, distancia);
        Assertions.assertEquals(coordenadaEsperada.getX(), coordenadaObtenida.getX());
        Assertions.assertEquals(coordenadaEsperada.getY(), coordenadaObtenida.getY());
    }

}
