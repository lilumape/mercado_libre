# Sistema Solar Lejano #

Sistema que predice las condiciones climaticas de un sistema solar compuesto por tres planetas. 

El sistema se basa en las siguientes premisas:

●  El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
horario. Su distancia con respecto al sol es de 500Km.

●  El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
horario. Su distancia con respecto al sol es de 2000Km.

●  El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
anti­horario, su distancia con respecto al sol es de 1000Km.

●  Todas las órbitas son circulares.

Las condiciones climaticas estan dadas por:

Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
sistema solar experimenta un período de sequía.

Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el
momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un
período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en
su máximo.

Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
alineados entre sí pero no están alineados con el sol.

El sistema permite saber:

1. Cuántos períodos de sequía habrá.
2. Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia.
3. Cuántos períodos de condiciones óptimas de presión y temperatura habrá.

El sistema ofrece una API REST para conocer la condición climatica de un dia en particular.

### Desarrollo del sistema  ###

Para reponder a las tres preguntas anteriores el programa ejecuta los siguientes procesos:

1. Se convierten las coordenadas polares a rectangulares o cartesianas.
2. Partiendo del hecho que los tres planetas comienzan su rotación encontrandose en el mismo eje del plano cartesiano y al mismo tiempo.
3. Para saber si los planetas estan alineados se calcula la ecuación de la recta tomando como referencia dos planetas y  verificando si el tercer planeta esta contenido en la recta hallada.
4. Para saber si el sol esta alineado se comprueba si este esta contenido en la ecuación de la recta hallada en el punto anterior.
5. En caso que no se cumplan las condiciones del punto 3 o 4 se calcula el area del triangulo que forman los tres planetas y se revisa si el sol esta contenido en este.

El calculo de predicción climatica de los primeros diez años se realiza cuando el programa inicia. 

Todos los dias a las 3 am ( "0 0 3 * * *"  ) se ejecuta un job que predice la condidición climatica de los diez años en adelante.


### Resultados obtenidos ###

Los resultados se encuentran almacenados en una BD embebida llamada "sistema-solar". En la tabla "prediccion" se encuentra el detalle por día y en la vista "consolidado" se encuentra la información que
responde a las tres preguntas anteriores y que se muestra a continuación:

PERIODOS_SEQUIA: 40

PERIODOS_LLUVIA: 80

PERIODOS_OPTIMOS: 0

DIA PICO DE LLUVIA esta dado por la condición

78 + 180x	0 < x < 19  102 + 180x

Siendo x= El número del día-1

### Detalle técnico ###

* Base de datos: SQLite
* Spring Boot 2.2.6.RELEASE
* JDK 1.8
* Reglas del código se encuentran en el archivo "checkstyle" en la raíz del proyecto y siguen la guía  https://docs.oracle.com/javase/specs/jls/se11/html/index.html
* Pruebas Unitarias: Junit 5 con Mockito
* Cobertura de código general del proyecto 96%

### Configuraciones ###

Para crear la base de datos se debe habilitar la propiedad "spring.jpa.hibernate.ddl-auto=create" en el archivo de propiedades de la aplicación, adicional se debe ejecutar el script
data.sql que se encuentra  en la raiz del proyecto.

### Compilación y ejecución ###

Para generar el archivo Jar con todo lo necesario para ejecutar el proyecto sin requerir de un servidor de aplicaciones, ejecutar la siguiente tarea maven:

mvn clean compile test package


Url API Rest: https://sistema-solar-mary.herokuapp.com/api/v1/climas/dias/${dia}

### Documentación ###

 * JavaDoc: [https://maryperez.bitbucket.io](https://maryperez.bitbucket.io)

* CI: Herokuapp https://dashboard.heroku.com/apps/sistema-solar-mary

* Contrato del servicio: https://sistema-solar-mary.herokuapp.com/api/v1/swagger-ui.html

### Ejecución CI/CD###

Una vez se sube el código en GitHub este se comprueba con Travis y se despliega automaticamente en Herokuapp. 


### Manejo de excepciones ###

El proyecto tiene implementado un manejador dentro del paquete config llamado "ExcepcionHandler" el cual se encarga de capturar todas las excepciones no controladas que se generen, almacenarlas en log del sistema con el cotenido de la excepción junto con un codigo unico de identificación del error, este mensaje junto con el código de error es retornado como objeto JSON como error 500, con este código unico de error el clinte puede reportar el error y asi poder ubicar el error en logs junto con toda la traza. 

Dentro de las propiedades de configuración del proyecto (archivo .properties) se encuentra una llave llamada "app.api.mostrarexcepciones" que permite que sea o no visible el contenido del error dentro del JSON de respuesta. En producción se recomienda colocar esta propiedad en false para que se muestre un error generico y asi no exponer mensajes de error técnicos los cuales pueden generar vulnerabilidades de seguridad.

Teniendo en cuenta que ese manejador existe en el proyecto, las excepciones que no puedan ser controladas deben ser disparadas hacia arriba de la pila de llamados sea utilizando la clausula throws en la firma del método o capturando la excepción y disparando una excepción de tipo Runtime para que sea capturada por el manejador.

### Manejo de logs del sistema ###

Todos los logs de sistema es decir logs que manejen excepciones o información de debug de la aplicación deben ser manejados utilizando slf4j, para esto en la clase donde se requiera el log se debe colocar la anotación de lombook @Slf4j a nivel de clase y dentro de la clase utilizar el objeto log para generar los logs.

Adicionalmente para facilitar el debug de la aplicación en la primera línea de cada método se debe agregar la siguiente línea:

LogTrace.trace(log)