/*
* Archivo: Coordenada
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
import static co.com.mercadolibre.sistema.solar.utilidades.Constantes.GRADOS_CIRCUNFERENCIA;
import java.math.BigDecimal;


/**
 * Clase utilitaria para maejo de coodenadas.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public final class CoordenadaUtil {

    /**
     * Contructor.
     */
    private CoordenadaUtil() {

    }

    /**
     * Convierte coordenadas polares a rectangulares. Los decimales se manejan
     * con seis cifras significativas y con aproximacion hacia arriba
     *
     * @param grados Grados de rotacion del objeto
     * @param radio Distancia del objeto al centro de la circunferencia
     * @return Coordenadas rectangulares
     */
    public static Coordenada convertirARectangular(int grados, long radio) {

        int medicionAngulo = getMedicionAngulo(grados);
        double medicionAnguloRadianes = Math.toRadians(medicionAngulo);
        BigDecimal x = BigDecimal.valueOf(radio * Math.cos(medicionAnguloRadianes));
        BigDecimal y = BigDecimal.valueOf(radio * Math.sin(medicionAnguloRadianes)); 
        return new Coordenada(x, y);
    }

    /**
     * Mide el angulo en el sentido contrario a las manecillas del reloj
     * partiendo desde el eje X.
     *
     * @param grados Grados de rotacion del objeto
     * @return Medicion del angulo
     */
    public static int getMedicionAngulo(int grados) {

    
        /**
         * Se toma que la rotacion del objeto comienza en el eje Y y la medicion
         * en el X.
         */
        final int gradosDesface = 90;

        //Angulos que giran en el sentido de las manecillas del reloj
        if (grados > 0) {
            //Primer cuadrante del plano cartesiano
            if (grados <= gradosDesface) {
                return gradosDesface - grados;
            }
            return Math.abs(grados - gradosDesface - GRADOS_CIRCUNFERENCIA);
        } else {
            return Math.abs(grados - gradosDesface);
        }
    }

}
