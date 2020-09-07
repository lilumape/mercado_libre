/*
* Archivo: ClimaController
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
package co.com.mercadolibre.sistema.solar.api;

import co.com.mercadolibre.sistema.solar.entidades.Clima;
import co.com.mercadolibre.sistema.solar.modelos.ErrorResponse;
import co.com.mercadolibre.sistema.solar.servicios.PronosticadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador del clima.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@Api(value = "climas")
@RequestMapping("/climas")
@RestController
@Slf4j
@Validated
public class ClimaApiController {

    /**
     * Servicio de pronostico del clima.
     */
    private final PronosticadorService pronosticadorService;

    /**
     * Constructor.
     * @param pronosticadorService PronosticadorService
     */
    public ClimaApiController(PronosticadorService pronosticadorService) {
        this.pronosticadorService = pronosticadorService;
    }

    /**
     * Operacion que pronostica el clima para un dia.
     *
     * @param dia Dia a buscar
     * @return ResponseEntity<Clima>
     */
    @ApiOperation(value = "Consulta la condición climatica de un día en particular", nickname = "dias",
            notes = "", response = Clima.class, tags = {"dias"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "se realiza el proceso de consulta de la prediccion",
                response = Clima.class),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 400, message = "Error en la peticion", response = ErrorResponse.class),
        @ApiResponse(code = 403, message = "Access Denied"),
        @ApiResponse(code = 404, message = "Not Found"),        
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)})

    @RequestMapping(value = "/dias/{dia}",
            produces = {"application/json"}, 
            method = RequestMethod.GET)
    ResponseEntity<Clima> dias(@PathVariable @Pattern(regexp = "[0-9]+") String dia) {
        return new ResponseEntity<>(pronosticadorService.consultarDia(Long.valueOf(dia)), HttpStatus.OK);
    }

}
