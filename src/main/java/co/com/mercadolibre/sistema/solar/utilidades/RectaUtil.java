/*
* Archivo: RectaUtil
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
import static co.com.mercadolibre.sistema.solar.utilidades.Constantes.PRECISION;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase utilitaria para maejo de rectas.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public final class RectaUtil {

    /**
     * Constructor.
     */
    private RectaUtil() {

    }

    /**
     * Devuelve la pendiente de una recta.
     *
     * @param p1 Coordenda de primer punto
     * @param p2 Coordenada de segundo punto
     * @return Pendiente
     */
    public static BigDecimal getPendiente(Coordenada p1, Coordenada p2) {

        BigDecimal divisor = p2.getX().subtract(p1.getX());
        if (divisor.doubleValue() == 0.0) {
            return BigDecimal.valueOf(0);
        }
        return p2.getY().subtract(p1.getY()).divide(divisor, PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Devuelve el punto de corte de una recta.
     *
     * @param p Punto conocido de la recta
     * @param pendiente Valor de la pendiente
     * @return Punto de corte
     */
    public static BigDecimal getPuntoCorte(Coordenada p, BigDecimal pendiente) {
        return p.getY().subtract(pendiente.multiply(p.getX()));

    }

    /**
     * Dtermina si un punto esta contenido en una recta.
     *
     * @param p Punto a analizar
     * @param pendiente Pendiente de la recta
     * @param puntoCorte Punto de corte de la recta
     * @return Devuelve true si el punto esta contenido
     */
    public static boolean puntoContenidoEnRecta(Coordenada p, BigDecimal pendiente, BigDecimal puntoCorte) {
        BigDecimal y = pendiente.multiply(p.getX()).add(puntoCorte);
        BigDecimal diferencia = y.subtract(p.getY());
        diferencia = diferencia.setScale(PRECISION, RoundingMode.HALF_EVEN);
        return diferencia.doubleValue() == 0;
    }

}
