/*
* Archivo: Clima
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
package co.com.mercadolibre.sistema.solar.modelos;

/**
 * Climas disponibles.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public enum ClimaOpciones {
    /**
     * Sequia.
     */
    SEQUIA(1),
    /**
     * Lluvia.
     */
    LLUVIA(2),
    /**
     * Condiciones optimas.
     */
    CONDICION_OPTIMA(3),
    /**
     * No definido en el documento.
     */
    INDEFINIDO(4);

    /**
     * Identificador del clima.
     */
    private int id;

    /**
     * Constructor.
     *
     * @param id Identificador del clima
     */
    ClimaOpciones(int id) {
        this.id = id;
    }

    /**
     * Id del clima.
     *
     * @return Id del clima
     */
    public int getId() {
        return id;
    }

}
