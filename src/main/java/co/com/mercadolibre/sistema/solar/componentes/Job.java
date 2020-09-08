/*
* Archivo: Job
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
package co.com.mercadolibre.sistema.solar.componentes;

import co.com.mercadolibre.sistema.solar.modelos.Planeta;
import co.com.mercadolibre.sistema.solar.repositorios.PrediccionRepository;
import co.com.mercadolibre.sistema.solar.servicios.impl.PronosticadorServiceImpl;
import co.com.mercadolibre.sistema.solar.utilidades.Constantes;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job del sistema solar.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@Component
@Slf4j
public class Job {

    /**
     * Repositorio de predicciones.
     */
    private final PrediccionRepository prediccionRepository;
    /**
     * Servicio de pronostico.
     */
    private final PronosticadorServiceImpl pronosticadorService;

    /**
     * Constructor.
     *
     * @param prediccionRepository Repositorio de predicciones
     * @param pronosticadorService Servicio de pronostico de clima
     */
    public Job(PrediccionRepository prediccionRepository, PronosticadorServiceImpl pronosticadorService) {
        this.prediccionRepository = prediccionRepository;
        this.pronosticadorService = pronosticadorService;
    }

    /**
     * Carga el pronostico climatico de los diez primeros años del sistema solar
     * en la BD.
     */
    @PostConstruct
    public void cargarDatosIniciales() {
        /**
         * Dia incial a cargar.
         */
        final long diaInicial = 1;
        /**
         * Dia final a cargar correspondiente a 10 años del sistema solar. Se
         * asume que todos los planetas empiezan a orbitar al tiempo y estando
         * en el mismo eje cartesiano
         */
        final long diaFinal = Planeta.FERENGI.getPosicion() * Constantes.GRADOS_CIRCUNFERENCIA * 10;
        long totalDias = prediccionRepository.count();
        //Solo se carga la informacion en caso que no haya sido cargada previamente
        if (totalDias == 0) {
            pronosticadorService.guardarPronostico(diaInicial, diaFinal);
        }
    }

    /**
     * Calcula el clima del dia siguiente al que se encuentra guardado en la BD.
     * Esta tarea es ejecutada todos los dias
     */
    @Scheduled(cron = "0 0 3 * * *")
    public void calcularClimaDiaSiguiente() {
        long ultimoDia = prediccionRepository.count();
        long diaConsultar = ultimoDia + 1;
        pronosticadorService.guardarPronostico(diaConsultar, diaConsultar);
    }

}
