/*
* Archivo: Prediccion
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
package co.com.mercadolibre.sistema.solar.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@Entity
@Table(name = "prediccion")
@Data
public class Prediccion implements Serializable {

    /**
     * Identificador de la tabla.
     */
    @Id
    @Column(name = "dia")
    private Long dia;
    /**
     * Numero del periodo al que pertenece el dia.
     */
    @Column(name = "periodo")
    private Long periodo;
    /**
     * Porcentaje de intensidad de lluvia.
     */
    @Column(name = "intensidad_lluvia")
    private Double intensidadLluvia;
    /**
     * Hora en el que se registra la prediccion.
     */
    @Column(name = "hora_registro")
    private LocalDateTime horaRegistro;
    /**
     * Clima pronosticado.
     */
    @OneToOne
    private Clima clima;

}
