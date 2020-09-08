/*
* Archivo: PronosticadorService
* Fecha: 2/09/2020
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
package co.com.mercadolibre.sistema.solar.servicios.impl;

import co.com.mercadolibre.sistema.solar.componentes.PronosticadorClimaHandler;
import co.com.mercadolibre.sistema.solar.entidades.Clima;
import co.com.mercadolibre.sistema.solar.entidades.Prediccion;
import co.com.mercadolibre.sistema.solar.excepciones.ExcepcionOpciones;
import static co.com.mercadolibre.sistema.solar.excepciones.ExcepcionOpciones.NO_GUARDA_PRONOSTICO;
import co.com.mercadolibre.sistema.solar.excepciones.SistemaRuntimeException;
import co.com.mercadolibre.sistema.solar.modelos.ClimaResponse;
import co.com.mercadolibre.sistema.solar.repositorios.PrediccionRepository;
import co.com.mercadolibre.sistema.solar.servicios.PronosticadorService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Servicio pronosticador.
 *
 * @author Mary Perez <lperez@grupoasd.com.co>
 */
@Service
@Slf4j
public class PronosticadorServiceImpl implements PronosticadorService {

    /**
     * Manejador del clima.
     */
    private final PronosticadorClimaHandler handler;
    /**
     * Repositorio de prediccion.
     */
    private final PrediccionRepository prediccionRepository;

    /**
     * Constructor.
     *
     * @param handler Manejador del clima
     * @param prediccionRepository Repositorio de prediccion
     */
    public PronosticadorServiceImpl(PronosticadorClimaHandler handler,
            PrediccionRepository prediccionRepository) {
        this.handler = handler;
        this.prediccionRepository = prediccionRepository;
    }

    /**
     * Consulta el pronostico de un dia en particular. Se cachea la consulta
     *
     * @param dia Dia a consultar
     */
    @Override
    @Cacheable("consultaxDia")
    public ClimaResponse consultarDia(long dia) {
        Optional<Prediccion> prediccion = prediccionRepository.findById(dia);
        if (!prediccion.isPresent()) {
            throw new SistemaRuntimeException(ExcepcionOpciones.NO_EXISTEN_DATOS);
        }
        return new ClimaResponse(String.valueOf(dia), prediccion.get().getClima().getNombre());
    }

    /**
     * Guarda el pronostico de un dia.
     *
     * @param dia Dia
     */
    public void guardarPronostico(long dia) {
        Prediccion prediccion = handler.getPronosticoDia(dia);
        anadirPeriodo(prediccion);
        prediccion = prediccionRepository.save(prediccion);
        if (prediccion == null) {
            throw new RuntimeException(NO_GUARDA_PRONOSTICO.name());
        }
    }

    /**
     * Guarda el pronostico de un rango de dias.
     *
     * @param diaInicial Dia inicial a pronosticar
     * @param diaFinal Dia final a pronosticar
     */
    public void guardarPronostico(long diaInicial, long diaFinal) {

        Map<Clima, Prediccion> periodosRegistrados = new HashMap<>();

        for (long dia = diaInicial; dia <= diaFinal; dia++) {
            Prediccion prediccion = handler.getPronosticoDia(dia);
            anadirPeriodo(prediccion, periodosRegistrados);
            prediccion = prediccionRepository.save(prediccion);

            if (prediccion == null) {
                throw new RuntimeException(NO_GUARDA_PRONOSTICO.name());
            }
        }
    }

    /**
     * Obtiene el ultimo periodo registrado. Los resultados de estos periodos
     * pueden ser consultados en la vista consolidados de la BD
     *
     * @param prediccion Prediccion a revisar
     * @param periodosRegistrados Periodos registrados
     */
    private void anadirPeriodo(Prediccion prediccion, Map<Clima, Prediccion> periodosRegistrados) {

        Prediccion ultimaPrediccion = periodosRegistrados.get(prediccion.getClima());
        Long ultimoDiaRegis = ultimaPrediccion == null ? 0 : ultimaPrediccion.getDia();
        Long periodo = ultimaPrediccion == null ? 0 : ultimaPrediccion.getPeriodo();

        if (ultimoDiaRegis + 1 != prediccion.getDia()) {
            periodo = periodo + 1;
        }
        prediccion.setPeriodo(periodo);
        periodosRegistrados.put(prediccion.getClima(), prediccion);
    }

    /**
     * Obtiene el ultimo periodo registrado.
     *
     * @param prediccion Prediccion a revisar
     * @return Ultimos periodo
     */
    private long anadirPeriodo(Prediccion prediccion) {

        Long periodo = 0L;
        Optional<Prediccion> optional = prediccionRepository.findById(prediccion.getDia() - 1);
        if (optional.isPresent()) {
            Prediccion ultimaPrediccion = optional.get();
            periodo = ultimaPrediccion.getPeriodo();
            if (ultimaPrediccion.getDia() + 1 != prediccion.getDia()) {
                periodo = periodo + 1;
            }
        }
        return periodo;
    }

}
