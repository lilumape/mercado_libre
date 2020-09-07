/*
* Archivo: JobTest
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

import co.com.mercadolibre.sistema.solar.repositorios.PrediccionRepository;
import co.com.mercadolibre.sistema.solar.servicios.impl.PronosticadorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test del Job.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@ExtendWith(MockitoExtension.class)
public class JobTest {

    /**
     * Clase a probar.
     */
    @InjectMocks
    private Job job;

    /**
     * Repositorio de predicciones.
     */
    @Mock
    private PrediccionRepository prediccionRepository;
    /**
     * Servicio de pronostico.
     */
    @Mock
    private PronosticadorServiceImpl pronosticadorService;

    @DisplayName("Carga los datos iniciales del rango de diez años")
    @Test
    public void cargarDatosIniciales() {
        Mockito.when(prediccionRepository.count()).thenReturn(Long.valueOf(0));
        Mockito.doNothing().when(pronosticadorService).guardarPronostico(Mockito.anyLong(), Mockito.anyLong());
        job.cargarDatosIniciales();
    }

    @DisplayName("Calcula un dia siguiente al almacenado en la BD")
    @Test
    public void calcularClimaDiaSiguiente() {
        Mockito.doNothing().when(pronosticadorService).guardarPronostico(Mockito.anyLong(), Mockito.anyLong());
        job.calcularClimaDiaSiguiente();
    }

}
