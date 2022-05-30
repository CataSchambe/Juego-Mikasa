.Presentación
image::caratula.png[]

 

Lara Micaela <micaelalara7u7@gmail.com> *Legajo: 44173253*

Rempel Marcos <marcosdrempel02@gmail.com>  *Legajo: 44097748*

Schamberger Catalina <catalinaschamberger@hotmail.com.ar> *Legajo: 44505881*

= Introducción 

El trabajo práctico se basa en la programación de un juego llamado Attack on Titan, Final Season,el cual ocurre en la Isla Paradis donde hay unos malvados gigantes de forma humanoide, llamados kyojines,que invaden las ciudades y aplastan todo a su paso.
Para combatir a los kyojines las Fuerzas Armadas de la Humanidad crearon La Legión de Reconocimiento encargada de exterminarlos.
Mikasa Ackerman,sera nuestra heroína y el personaje principal del juego por así decirlo tiene la tarea de exterminar a todos los kyojines que invadieron la ciudad.
Para exterminarlos cuenta con un proyectil que lanza para combatir a los kyojines y existe un suero el suero llamado kyojin no kessei,capaz de transformar temporalmente a una persona en un kyojin entonces cuando Mikasa lo toma se transforma temporalmente en una kyojina,lo que le brinda poderes. Si un kyojin toca a Mikasa, cuando se encuentra en estado de kyojina, el kyojin muere y Mikasa vuelve a su estado normal, luego nos dimos cuenta que podiamos hacerla con uno de los metodos
echos en clase que nos permitia eliminar elementos de una lista y de esta manera si el proyectil toca al kyojin el kyojin "muere" y desaparece de la pantalla 

.Regeneracion de Kyojines
[source, java]
------
if (intervaloKyojines % 960 == 0) { // chequea la cantidad de kyojines cada aprox 15 segundos
	for (int i = 0; i < kyojines.length; i++) {
		if (kyojines[i] == null) {
		kyojines[i] = new Kyojin((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
			(Math.random() * ((entorno.alto() - 100) - 100) + 100), 0.3);
		kyojinesEnPantalla ++;
			for (int j = 0; j < obstaculos.length; j++) {
					if (kyojines[i].chocasteConUnObstaculo(obstaculos[j])) {
					kyojines[i] = null;
					kyojinesEnPantalla -- ;
							
						}
					}
				}
			}
		}
------

Luego tuvimos complicaciones con las colisiones del proyectil ya que si bien andaba bien el juego y demas, internamente nos tiraba excepsiones y errores, pero sorprendentemente se soluciono añadiendo un return al final del metodo

Para programar el juego contamos con un Apéndice de implementación base y un entorno

= Descripción 
Principalmente tuvimos inconvenientes en decidir que forma le dábamos a mikasa (circulo, rectángulo, triangulo, etc..) pero por suerte nos
logramos poner de acuerdo en que seria mucho mas fácil que sea un circulo para poder darle movimiento mediante un angulo mediante trigonometría 
y pitagoras 
Luego se nos hizo muy complicado el tema de las colisiones tanto los kyojines entre ellos, como mikasa con los obstáculos o los kyojines con
los obstáculos, por suerte logramos darnos cuenta en donde estaba el error debatiendo bien a quien había que preguntarle "che chocaste
con ..." 
Luego se nos dificulto la generación de los kyojines y del proyectil, ya que con el proyectil en primer momento solo se lanzaba por 1 segundo,pero lo arreglamos implementando un if  
Los últimos problemas que se nos presentaron fueron es el comportamiento de los kyijines,como hacer para que mikasa los mate y como hacer para que en un determinado tiempo se regeneren 

Nos pareció buena idea sumarle una pantalla de inicio para arrancar el juego y luego al finalizar que muestre el típico cartel de "GAME OVER"

= Implementación 
Una sección de implementación donde se incluya el código fuente correctamente 
formateado y comentado, si corresponde.

= Conclusiones 
Algunas reflexiones acerca del desarrollo del trabajo realizado y de los resultados obtenidos.
Pueden incluirse lecciones aprendidas durante el desarrollo del trabajo.


