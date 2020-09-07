/*
* Archivo: Coordenada
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
package co.com.mercadolibre.sistema.solar.modelos;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Modelo del triangulo.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@Data
public class Coordenada {

    /**
     * Constructor.
     *
     * @param x Coordenada en el eje X
     * @param y Coordenada en el eje Y
     */
    public Coordenada(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Coordenada en el eje X.
     */
    private BigDecimal x;
    /**
     * Coordenada en el eje Y.
     */
    private BigDecimal y;

}
