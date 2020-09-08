/*
* Archivo: ClimaResponse
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

import lombok.Data;

/**
 * Respuesta del servicio.
 *
 * @author Mary Perez <lperez@grupoasd.com.co>
 */
@Data
public class ClimaResponse {

    /**
     * Dia consultado.
     */
    private String dia;
    /**
     * Nombre del clima.
     */
    private String clima;

    /**
     * Constructor.
     *
     * @param dia Dia consultado.
     * @param clima Nombre del clima.
     */
    public ClimaResponse(String dia, String clima) {
        this.dia = dia;
        this.clima = clima;
    }

}
