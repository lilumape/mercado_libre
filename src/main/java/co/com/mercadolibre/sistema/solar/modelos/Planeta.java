/*
* Archivo: Planeta
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

import static co.com.mercadolibre.sistema.solar.utilidades.Constantes.GRADOS_CIRCUNFERENCIA;

/**
 * Planetas habilitados.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
public enum Planeta {
    /**
     * Planeta mas proximo al sol.
     */
    FERENGI(500, 1),
    /**
     * Planeta mas lejano al sol.
     */
    BETASOIDE(2000, 3),
    /**
     * Planeta intermedio al sol.
     */
    VULCANO(1000, -5);

    /**
     * Devuelve la posicion angular de un planeta orbitando al sol. Las unidades
     * estan dandas en grados
     *
     * @return Posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Devuelve la posicion angular de un planeta orbitando al sol. Las unidades
     * estan dandas en grados
     *
     * @param dias Dias de orbitacion
     * @return Posicion del planeta
     */
    public int getPosicion(long dias) {

        int totalDiasAno = Math.abs(GRADOS_CIRCUNFERENCIA / posicion);
        long posicionActual = Math.abs(posicion * dias);
        long anoActual = posicionActual / GRADOS_CIRCUNFERENCIA;
        long anoEnDias = anoActual * totalDiasAno;
        int signo = posicion < 0 ? -1 : 1;
        int diaUltimoAno = (int) (dias - anoEnDias);
        return (int) (posicionActual > GRADOS_CIRCUNFERENCIA ? diaUltimoAno * posicion
                : posicionActual * signo);
    }

    /**
     * Devuelve la distancia de un planeta al sol. Las unidades estan dadas en
     * kilometros
     *
     * @return Distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Distancia del planeta al sol.
     */
    private final int distancia;
    /**
     * Posicion angular del planeta orbitando al sol.
     */
    private final int posicion;

    /**
     * Constructor.
     *
     * @param distancia Distancia del planeta al sol
     * @param posicion Posicion del planeta en el sistema solar
     */
    Planeta(int distancia, int posicion) {
        this.distancia = distancia;
        this.posicion = posicion;
    }
}
