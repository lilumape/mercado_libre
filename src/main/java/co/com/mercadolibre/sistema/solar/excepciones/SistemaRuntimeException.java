/*
* Archivo: SistemaSolarException
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
package co.com.mercadolibre.sistema.solar.excepciones;

/**
 * Excepciones del sistema solar.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */

public class SistemaRuntimeException extends RuntimeException {

    /**
     * @return the excepcion Excepcion.
     */
    public ExcepcionOpciones getExcepcion() {
        return excepcion;
    }

    /**
     * Excepcion generada.
     */
    private final ExcepcionOpciones excepcion;

    /**
     * Constructor.
     *
     * @param excepcion ExcepcionOpciones
     */
    public SistemaRuntimeException(ExcepcionOpciones excepcion) {
        super();
        this.excepcion = excepcion;
    }

}
