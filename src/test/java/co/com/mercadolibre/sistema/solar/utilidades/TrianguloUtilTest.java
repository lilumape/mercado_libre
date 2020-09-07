/*
* Archivo: TrianguloTest
* Fecha: 2020/08/31
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
 * Test unitario de la clase TrianguloUtil.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class TrianguloUtilTest {

    @DisplayName(value = "Valida que un punto este dentro de un triangulo")
    @Test
    public void testPuntoDentroArea() {
        //Vertice 1
        Coordenada p1 = new Coordenada(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        //Vertice 2
        Coordenada p2 = new Coordenada(BigDecimal.valueOf(4), BigDecimal.valueOf(2));
        //Vertice 3
        Coordenada p3 = new Coordenada(BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        //Punto a encontrar
        Coordenada p4 = new Coordenada(BigDecimal.valueOf(3), BigDecimal.valueOf(2));

        boolean puntoDentroArea = TrianguloUtil.puntoDentroArea(p1, p2, p3, p4);
        Assertions.assertTrue(puntoDentroArea);

    }

    @DisplayName(value = "Valida que un punto este fuera de un triangulo")
    @Test
    public void testPuntoFueraArea() {
        //Vertice 1
        Coordenada p1 = new Coordenada(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        //Vertice 2
        Coordenada p2 = new Coordenada(BigDecimal.valueOf(4), BigDecimal.valueOf(2));
        //Vertice 3
        Coordenada p3 = new Coordenada(BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        //Punto a encontrar
        Coordenada p4 = new Coordenada(BigDecimal.valueOf(3), BigDecimal.valueOf(1));

        boolean puntoDentroArea = TrianguloUtil.puntoDentroArea(p1, p2, p3, p4);
        Assertions.assertFalse(puntoDentroArea);

    }

    @DisplayName(value = "Area  mayor entre dos triangulos")
    @Test
    public void testAreaMayor() {

        //Triangulo Menor 
        Coordenada pt11 = new Coordenada(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        //Vertice 2
        Coordenada pt12 = new Coordenada(BigDecimal.valueOf(4), BigDecimal.valueOf(2));
        //Vertice 3
        Coordenada pt13 = new Coordenada(BigDecimal.valueOf(2), BigDecimal.valueOf(3));

        //Triangulo Mayor
        Coordenada pt21 = new Coordenada(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        //Vertice 2
        Coordenada pt22 = new Coordenada(BigDecimal.valueOf(4), BigDecimal.valueOf(2));
        //Vertice 3
        Coordenada pt23 = new Coordenada(BigDecimal.valueOf(2), BigDecimal.valueOf(5));

        double triangulo1 = TrianguloUtil.getArea(pt11, pt12, pt13);
        double triangulo2 = TrianguloUtil.getArea(pt21, pt22, pt23);
        boolean cumpleCondicion = triangulo2 > triangulo1;
        Assertions.assertTrue(cumpleCondicion);

    }

    @DisplayName(value = "Area de un triangulo")
    @Test
    public void testArea() {

        double areaEsperada =3.500000000000002;
        //Triangulo Menor 
        Coordenada pt11 = new Coordenada(BigDecimal.valueOf(1), BigDecimal.valueOf(0));
        //Vertice 2
        Coordenada pt12 = new Coordenada(BigDecimal.valueOf(4), BigDecimal.valueOf(2));
        //Vertice 3
        Coordenada pt13 = new Coordenada(BigDecimal.valueOf(2), BigDecimal.valueOf(3));

        double areaObtenida = TrianguloUtil.getArea(pt11, pt12, pt13);
        Assertions.assertEquals(areaEsperada, areaObtenida);

    }

}
