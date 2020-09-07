/*
* Archivo: TrianguloUtil
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

/**
 * Clase utilitaria para maejo de triangulos.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public final class TrianguloUtil {

    /**
     * Constructor.
     */
    private TrianguloUtil() {

    }

    /**
     * Determina si un punto esta contenido en el area de un triangulo. Los
     * decimales se manejan con seis cifras significativas y con aproximacion
     * hacia arriba
     *
     * @param p1 Primer vertice del triangulo
     * @param p2 Segundo vertice del triangulo
     * @param p3 Tercer vertice del triangulo
     * @param p4 Punto a analizar
     * @return Verdadero en caso que este contenido
     */
    public static boolean puntoDentroArea(Coordenada p1, Coordenada p2, Coordenada p3, Coordenada p4) {
        boolean orientacion0Positiva = isOrientacionPositiva(p1, p2, p3);
        boolean orientacion1Positiva = isOrientacionPositiva(p1, p2, p4);
        boolean orientacion2Positiva = isOrientacionPositiva(p2, p3, p4);
        boolean orientacion3Positiva = isOrientacionPositiva(p3, p1, p4);

        if (orientacion0Positiva) {
            return orientacion1Positiva && orientacion2Positiva && orientacion3Positiva;
        }
        return !orientacion1Positiva && !orientacion2Positiva && !orientacion3Positiva;
    }

    /**
     * Determina si la orientacion de un triangulo es positiva.
     *
     * @param p1 Vertice 1
     * @param p2 Vertice 2
     * @param p3 Vertice 3
     * @return Si la orientacion es positiva
     */
    public static boolean isOrientacionPositiva(Coordenada p1, Coordenada p2, Coordenada p3) {
        return (p1.getX().doubleValue() - p3.getX().doubleValue())
                * (p2.getY().doubleValue() - p3.getY().doubleValue())
                - (p1.getY().doubleValue() - p3.getY().doubleValue())
                * (p2.getX().doubleValue() - p3.getX().doubleValue()) > 0;

    }

    /**
     * Se obteiene el area siguiendo la formula de Heron. Los decimales se
     * manejan con seis cifras significativas y con aproximacion hacia arriba
     *
     * @param p1 Primer vertice del triangulo
     * @param p2 Segundo vertice del triangulo
     * @param p3 Tercer vertice del triangulo
     * @return Area
     */
    public static double getArea(Coordenada p1, Coordenada p2, Coordenada p3) {
        double lado1 = Math.sqrt(Math.pow(p1.getX().doubleValue() - p2.getX().doubleValue(), 2)
                + Math.pow(p1.getY().doubleValue() - p2.getY().doubleValue(), 2));
        double lado2 = Math.sqrt(Math.pow(p1.getX().doubleValue() - p3.getX().doubleValue(), 2)
                + Math.pow(p1.getY().doubleValue() - p3.getY().doubleValue(), 2));
        double lado3 = Math.sqrt(Math.pow(p2.getX().doubleValue() - p3.getX().doubleValue(), 2)
                + Math.pow(p2.getY().doubleValue() - p3.getY().doubleValue(), 2));
        double superPerimetro = (lado1 + lado2 + lado3) / 2;
        return Math.sqrt((superPerimetro * (superPerimetro - lado1))
                * (superPerimetro - lado2) * (superPerimetro - lado3));
    }
}
