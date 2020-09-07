/*
* Archivo: Mensajes
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

import org.springframework.http.HttpStatus;

/**
 * Mensajes manejados en la aplicacion.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public enum ExcepcionOpciones {

    /**
     * No se encontraron datos en la BD.
     */
    NO_EXISTEN_DATOS("100", HttpStatus.NO_CONTENT),
    /**
     * No se pudo guardar el pronostico en la BD.
     */
    NO_GUARDA_PRONOSTICO("500", HttpStatus.INTERNAL_SERVER_ERROR);

    /**
     * Codigo del mensaje.
     *
     * @return Codigo del mensaje
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Status de la peticion.
     *
     * @return Status de la peticion
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Codigo del mensaje.
     */
    private final String codigo;
    /**
     * Estado de la peticion.
     */
    private final HttpStatus status;

    /**
     * Constructor.
     *
     * @param codigo Codigo del mensaje
     * @param status Estado de la peticion
     */
    ExcepcionOpciones(String codigo, HttpStatus status) {
        this.codigo = codigo;
        this.status = status;
    }

}
