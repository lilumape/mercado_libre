/*
* Archivo: Clima
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity.
 *
 * @author Mary Perez <lperez@grupoasd.com.co>
 */
@Entity
@Table(name = "clima")
@Data
public class Clima implements Serializable {

    /**
     * Identificador de la tabla.
     */
    @Id
    @Column(name = "id_clima")
    private Integer idClima;

    /**
     * Nombre del clima.
     */
    @Column(name = "nombre")
    private String nombre;
}
