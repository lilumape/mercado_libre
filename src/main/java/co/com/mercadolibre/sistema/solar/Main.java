/*
* Archivo: Main
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
package co.com.mercadolibre.sistema.solar;

import co.com.mercadolibre.sistema.solar.servicios.impl.PronosticadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase de arranque.
 *
 * @author Mary Perez <lilumape@gmail.com>
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class Main {

    /**
     * Servicio.
     */
    @Autowired
    PronosticadorServiceImpl service;

    /**
     * Punto de arranque.
     *
     * @param args Argumentos.
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Manejador de cache.
     *
     * @return Objeto manejado del cache
     */
    @Bean
    public CacheManager cacheManager() {  
        ehCacheCacheManager();
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    /**
     * Fabrica de cache.
     *
     * @return Fabrica
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);
        return cmfb;
    }
}
