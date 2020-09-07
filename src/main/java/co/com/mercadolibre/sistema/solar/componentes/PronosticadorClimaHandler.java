/*
* Archivo: PronosticadorClimaHandler
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
package co.com.mercadolibre.sistema.solar.componentes;

import co.com.mercadolibre.sistema.solar.entidades.Clima;
import co.com.mercadolibre.sistema.solar.entidades.Prediccion;
import co.com.mercadolibre.sistema.solar.modelos.ClimaOpciones;
import co.com.mercadolibre.sistema.solar.modelos.Coordenada;
import co.com.mercadolibre.sistema.solar.utilidades.RectaUtil;
import co.com.mercadolibre.sistema.solar.utilidades.TrianguloUtil;
import static co.com.mercadolibre.sistema.solar.modelos.Planeta.BETASOIDE;
import static co.com.mercadolibre.sistema.solar.modelos.Planeta.FERENGI;
import static co.com.mercadolibre.sistema.solar.modelos.Planeta.VULCANO;
import static co.com.mercadolibre.sistema.solar.utilidades.CoordenadaUtil.convertirARectangular;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Pronosticador del clima.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@Component
public class PronosticadorClimaHandler {

    /**
     * Devuelve el clima pronosticado para un dia.
     *
     * @param dia Dia a pronosticar
     * @return Condicion climatica pronosticada
     */
    public Prediccion getPronosticoDia(long dia) {

        Coordenada coordenadaPF = convertirARectangular((int) FERENGI.getPosicion(dia), FERENGI.getDistancia());
        Coordenada coordenadaPV = convertirARectangular((int) VULCANO.getPosicion(dia), VULCANO.getDistancia());
        Coordenada coordenadaPB = convertirARectangular((int) BETASOIDE.getPosicion(dia), BETASOIDE.getDistancia());
        ClimaOpciones condicionDia = getClimaPlanetasAlineados(coordenadaPF, coordenadaPB, coordenadaPV);

        switch (condicionDia) {

            case CONDICION_OPTIMA:
                return getPrediccion(condicionDia, dia);

            case SEQUIA:
                return getPrediccion(condicionDia, dia);

            default:
                ClimaOpciones clima = getClimaPlanetasNoAlineados(coordenadaPF, coordenadaPB, coordenadaPV);
                if (clima == ClimaOpciones.LLUVIA) {
                    double intensidadLluvia = TrianguloUtil.getArea(coordenadaPF, coordenadaPV, coordenadaPB);
                    return getPrediccion(dia, intensidadLluvia);
                }
                return getPrediccion(clima, dia);

        }

    }

    /**
     * Devuelve la condicion del clima de planetas alineados.
     *
     * @param coordenadaPF Coordenda del planeta Ferengi
     * @param coordenadaPB Coordenda del planeta Betasoide
     * @param coordenadaPV Coordenada del planeta Vulcano
     * @return Condicion del dia
     */
    public ClimaOpciones getClimaPlanetasAlineados(Coordenada coordenadaPF, Coordenada coordenadaPB,
            Coordenada coordenadaPV) {

        BigDecimal pendiente = RectaUtil.getPendiente(coordenadaPF, coordenadaPV);
        BigDecimal puntoCorte = RectaUtil.getPuntoCorte(coordenadaPF, pendiente);
        Coordenada coordenadaSol = new Coordenada(BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        boolean tresPlanetasAlineados = planetaAlineado(coordenadaPB, pendiente, puntoCorte);
        boolean solContenidoRecta = planetaAlineado(coordenadaSol, pendiente, puntoCorte);
        boolean planetasAlineadosConSol = tresPlanetasAlineados && solContenidoRecta;

        if (planetasAlineadosConSol) {
            return ClimaOpciones.SEQUIA;
        }
        if (tresPlanetasAlineados) {
            return ClimaOpciones.CONDICION_OPTIMA;
        }
        return ClimaOpciones.INDEFINIDO;
    }

    /**
     * Devuelve la condicion del clima de planetas no alineados.
     *
     * @param coordenadaPF Coordenda del planeta Ferengi
     * @param coordenadaPB Coordenda del planeta Betasoide
     * @param coordenadaPV Coordenada del planeta Vulcano
     * @return Condicion del dia
     */
    public ClimaOpciones getClimaPlanetasNoAlineados(Coordenada coordenadaPF, Coordenada coordenadaPB,
            Coordenada coordenadaPV) {
        boolean estaSolEnMedioPlanetas = etsaSolEnMedioPlanetas(coordenadaPF, coordenadaPV, coordenadaPB);
        if (estaSolEnMedioPlanetas) {
            return ClimaOpciones.LLUVIA;
        }
        return ClimaOpciones.INDEFINIDO;
    }

    /**
     * Devuelve si el sol esta en medio de los planetas.
     *
     * @param coordenadaP1 Coordendas de posicion del primer planeta
     * @param coordenadaP2 Coordendas de posicion del segundo planeta
     * @param coordenadaP3 Coordendas de posicion del tercer planeta
     * @return Si el sol esta en medio de los planetas
     */
    private boolean etsaSolEnMedioPlanetas(Coordenada coordenadaP1, Coordenada coordenadaP2, Coordenada coordenadaP3) {
        Coordenada coordenadaSol = new Coordenada(BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        return TrianguloUtil.puntoDentroArea(coordenadaP1, coordenadaP2, coordenadaP3, coordenadaSol);
    }

    /**
     * Devuelve si un planeta esta contenido en la recta que forman los otros
     * dos.
     *
     * @param coordenadaP Coordenada del planeta a revisar
     * @param pendiente Pendiente de la recta
     * @param puntoCorte Punto de corte de la recta
     * @return Si el planeta esta contenido en la recta
     */
    private boolean planetaAlineado(Coordenada coordenadaP, BigDecimal pendiente, BigDecimal puntoCorte) {
        return RectaUtil.puntoContenidoEnRecta(coordenadaP, pendiente, puntoCorte);
    }

    /**
     * Devuelve un objeto Prediccion de lluvia.
     *
     * @param dia Dia analizado
     * @param intensidad Intensisdad de lluvia
     * @return Prediccion de lluvia
     */
    private Prediccion getPrediccion(Long dia, Double intensidad) {
        Prediccion prediccion = getPrediccion(ClimaOpciones.LLUVIA, dia);
        prediccion.setIntensidadLluvia(intensidad);
        return prediccion;
    }

    /**
     * Devuelve un objeto Prediccion.
     *
     * @param climaOpcion Clima obtenido
     * @param dia Dia analizado
     * @return Prediccion
     */
    private Prediccion getPrediccion(ClimaOpciones climaOpcion, Long dia) {
        Prediccion prediccion = new Prediccion();
        prediccion.setDia(dia);
        Clima clima = new Clima();
        clima.setIdClima(climaOpcion.getId());
        prediccion.setClima(clima);
        prediccion.setHoraRegistro(LocalDateTime.now());
        return prediccion;
    }

}
