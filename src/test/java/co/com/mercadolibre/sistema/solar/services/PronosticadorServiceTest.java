/*
* Archivo: PronosticadorServiceTest
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
package co.com.mercadolibre.sistema.solar.services;

import co.com.mercadolibre.sistema.solar.componentes.PronosticadorClimaHandler;
import co.com.mercadolibre.sistema.solar.entidades.Clima;
import co.com.mercadolibre.sistema.solar.entidades.Prediccion;
import co.com.mercadolibre.sistema.solar.excepciones.ExcepcionOpciones;
import co.com.mercadolibre.sistema.solar.excepciones.SistemaRuntimeException;
import co.com.mercadolibre.sistema.solar.servicios.impl.PronosticadorServiceImpl;
import co.com.mercadolibre.sistema.solar.modelos.Coordenada;
import co.com.mercadolibre.sistema.solar.repositorios.PrediccionRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test.
 *
 * @author Mary Perez <lperez@grupoasd.com.co>
 */
@ExtendWith(MockitoExtension.class)
public class PronosticadorServiceTest {

    /**
     * Repositorio de predicciones.
     */
    @Mock
    private PrediccionRepository prediccionRepository;
    /**
     * Servicio de pronostico de clima.
     */
    @InjectMocks
    private PronosticadorServiceImpl pronosticadorService;
    /**
     * Manejador del clima.
     */
    @Mock
    private PronosticadorClimaHandler handler;

    @DisplayName("Consultar dia existente")
    @Test
    public void consultarDia() {
        long dia = 1;
        Prediccion prediccion = new Prediccion();
        Clima clima = new Clima();
        prediccion.setClima(clima);
        Optional<Prediccion> optional = Optional.of(prediccion);
        Mockito.when(prediccionRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Clima climaObtenido = pronosticadorService.consultarDia(dia);
        Assertions.assertNotNull(climaObtenido);
    }

    @DisplayName("Consultar dia no existente")
    @Test
    public void consultarDiaNoExistente() {
        long dia = 1;
        Optional<Prediccion> optional = Optional.empty();
        Mockito.when(prediccionRepository.findById(Mockito.anyLong())).thenReturn(optional);
        SistemaRuntimeException exception = assertThrows(SistemaRuntimeException.class, () -> {
            pronosticadorService.consultarDia(dia);
        });
        assertEquals(ExcepcionOpciones.NO_EXISTEN_DATOS, exception.getExcepcion());

    }

    @DisplayName("Test diez años")
    @Test
    public void guardarPronosticoRango() {
        long diaInicial = 102;
        long diaFinal = 360 * 10;
        Prediccion prediicion = new Prediccion();
        Mockito.when(prediccionRepository.save(Mockito.any(Prediccion.class))).thenReturn(prediicion);
        Mockito.doCallRealMethod().when(handler).getPronosticoDia(Mockito.anyLong());
        Mockito.doCallRealMethod().when(handler).getClimaPlanetasAlineados(Mockito.any(Coordenada.class), Mockito.any(Coordenada.class), Mockito.any(Coordenada.class));
        Mockito.doCallRealMethod().when(handler).getClimaPlanetasNoAlineados(Mockito.any(Coordenada.class), Mockito.any(Coordenada.class),
                Mockito.any(Coordenada.class));
        pronosticadorService.guardarPronostico(diaInicial, diaFinal);
    }

    @DisplayName("Test un dia sin periodos pervios existentes")
    @Test
    public void guardarPronostico() {
        long dia = 3601;
        Prediccion prediicion = new Prediccion();
        Mockito.when(prediccionRepository.save(Mockito.any(Prediccion.class))).thenReturn(prediicion);
        Mockito.doCallRealMethod().when(handler).getPronosticoDia(Mockito.anyLong());
        Mockito.doCallRealMethod().when(handler).getClimaPlanetasAlineados(Mockito.any(Coordenada.class), Mockito.any(Coordenada.class), Mockito.any(Coordenada.class));
        Mockito.doCallRealMethod().when(handler).getClimaPlanetasNoAlineados(Mockito.any(Coordenada.class), Mockito.any(Coordenada.class),
                Mockito.any(Coordenada.class));
        pronosticadorService.guardarPronostico(dia);
    }

    @DisplayName("Test un dia con periodos pervios existentes")
    @Test
    public void guardarPronostico2() {
        long dia = 3601;
        Prediccion prediccion = new Prediccion();
        prediccion.setDia(1L);
        prediccion.setPeriodo(1L);
        Optional<Prediccion> optional = Optional.of(prediccion);
        Mockito.when(prediccionRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.when(prediccionRepository.save(Mockito.any(Prediccion.class))).thenReturn(prediccion);
        Mockito.doCallRealMethod().when(handler).getPronosticoDia(Mockito.anyLong());
        Mockito.doCallRealMethod().when(handler).getClimaPlanetasAlineados(Mockito.any(Coordenada.class), Mockito.any(Coordenada.class), Mockito.any(Coordenada.class));
        Mockito.doCallRealMethod().when(handler).getClimaPlanetasNoAlineados(Mockito.any(Coordenada.class), Mockito.any(Coordenada.class),
                Mockito.any(Coordenada.class));
        pronosticadorService.guardarPronostico(dia);
    }

}
