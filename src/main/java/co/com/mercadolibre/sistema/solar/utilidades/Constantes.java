/*
* Archivo: Constantes
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

/**
 * Constantes de la aplicacion.
 *
 * @author Mary Perez <lperez@grupoasd.com.co>
 */
public final class Constantes {

    /**
     * Constructor.
     */
    private Constantes() {

    }

    /**
     * Precicion de decimales.
     */
    public static final int PRECISION = 6;
    /**
     * Grados de una circunferencia.
     */
    public static final int GRADOS_CIRCUNFERENCIA = 360;
    /**
     * Mensaje de error por defecto para las excepciones.
     */
    public static final String EXCEPTION_MESSAGE = "Se ha presentado un error interno. "
            + "Por favor intente mas tarde. Si el error persiste, por favor informe del error "
            + "utilizando el valor de errorCode";
}
