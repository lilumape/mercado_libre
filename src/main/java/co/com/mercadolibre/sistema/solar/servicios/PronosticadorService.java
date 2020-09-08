/*
* Archivo: PronosticadorService
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
package co.com.mercadolibre.sistema.solar.servicios;

import co.com.mercadolibre.sistema.solar.modelos.ClimaResponse;

/**
 * Servicio de pronostico del clima.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public interface PronosticadorService {

    /**
     * Consulta el pronostico del clima un dia en particular.
     *
     * @param dia Dia a consultar
     * @return Clima encontrado
     */
    ClimaResponse consultarDia(long dia);

}
