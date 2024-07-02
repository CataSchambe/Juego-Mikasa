Schamberger Catalina <catalinaschamberger@hotmail.com.ar> *Legajo: 44505881*

=== Introducción

El trabajo práctico se basa en la programación de un juego llamado _Attack
on Titan, Final Season_, el cual ocurre en la Isla Paradis donde hay unos malvados gigantes de forma humanoide, llamados kyojines,que invaden las ciudades y aplastan todo a su paso.

Para combatir a los kyojines las Fuerzas Armadas de la Humanidad crearon La Legión de Reconocimiento encargada de exterminarlos.

Mikasa Ackerman sera nuestra heroína y el personaje principal del juego por así decirlo, tiene la tarea de exterminar a todos los kyojines que invadieron la ciudad.Para exterminarlos cuenta con un proyectil que lanza para combatir a los kyojines y existe un suero el suero llamado kyojin no kessei,capaz de transformar temporalmente a una persona en un kyojin, entonces cuando Mikasa lo toma se transforma temporalmente en una kyojina, lo que le brinda poderes. Si un kyojin toca a Mikasa, cuando se encuentra en estado de kyojina, el kyojin muere y Mikasa vuelve a su estado normal.

Para programar el juego contamos con un Apéndice de implementación base y un entorno.

=== Descripción

Principalmente tuvimos inconvenientes en decidir que forma le dábamos a Mikasa (círculo, rectángulo, triángulo, etc.) pero por suerte nos logramos poner de acuerdo en que sería mucho más fácil que sea un circulo para poder darle movimiento mediante un angulo mediante trigonometría.


.movimiento de mikasa
----
    public void avanzar() {
		x = x + Math.cos(angulo) * velocidad;
		y = y + Math.sin(angulo) * velocidad;
	}
----

Luego se nos hizo muy complicado el tema de las colisiones tanto los kyojines entre ellos
como mikasa con los obstáculos, ó los kyojines con los obstáculos, pero
por suerte logramos darnos cuenta en donde estaba el error debatiendo bien a quien había que preguntarle "che chocaste
con ...".

Luego se nos dificulto la generación de los kyojines y del proyectil, ya que con el proyectil en primer momento solo se lanzaba por 1 segundo, pero lo arreglamos implementando un if en la clase juego.
----
.Creacion del proyectil
[source, java]
    if (proyectil != null) {
    proyectil.dibujar(entorno);
    for (Obstaculo o : obstaculos) {
			 if (proyectil.chocasteConObstaculo(o)) {
				proyectil = null;
				return;
				}
			}

			if (proyectil.chocasteCon(entorno)) {
				proyectil = null;
				return;
			}
			proyectil.avanzar();
		}
----
  
Los últimos problemas que se nos presentaron fue como hacer
como hacer para que en un determinado tiempo se regeneren, luego nos
dimos cuenta que podíamos hacerla con uno de los métodos hechos en clase
que nos permitía eliminar elementos de un array y de esta manera si el
proyectil toca al kyojin, el kyojin "muere" y desaparece de la pantalla. 

En la clase juego

----
.Regeneracion de Kyojines
[source, java]
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
----
Después tuvimos complicaciones con las colisiones del proyectil ya que
si bien andaba bien el juego y demás, internamente nos tiraba
excepciones y errores, pero sorprendentemente se solucionó añadiendo un
return al final del método. Luego aparecieron más excepciones, llegando a un punto de que los errores hacian al juego injugable, lo que llevo a una revision entera del codigo para llegar a la conclusion de que habia que preguntar siempre si el objeto a comparar no era un null.

Nos pareció buena idea sumarle una pantalla al finalizar que muestre el típico cartel de "GAME OVER" y otro cartel que indique cuando ganaste. Con esto tambien tuvimos un problema de entrada, ya que se ejecutaban los carteles pero el metodo tick seguia corriendo. Para esto tuvimos que preguntar de entrada si mikasa existia y si habían kyojines en la pantalla.

*Implementación* 

----
.Clase Juego
[source, java]
package juego;

import entorno.Herramientas;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	private Mikasa mikasa;
	private Kyojin[] kyojines;

	private Obstaculo[] obstaculos;
	private Suero suero;
	private Proyectil proyectil;

	private int tiempoDeJuego = 0;
	private int tiempoDeSuero = 0;
	private int intervaloKyojines;
	private int segundos = 0;

	private int kyojinesEliminados = 0;
	private int kyojinesEnPantalla;

	private Image fondo;
	private Image fondoVictoria;
	private Image fondoGameOver;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 600);
		this.mikasa = new Mikasa(entorno.ancho() / 2, entorno.alto() / 2, 2, 0);

		// generación de obstaculos (fijos)
		obstaculos = new Obstaculo[5];

		obstaculos[0] = new Obstaculo(115, 397);
		obstaculos[1] = new Obstaculo(427, 121);
		obstaculos[2] = new Obstaculo(700, 520);
		obstaculos[3] = new Obstaculo(178, 106);
		obstaculos[4] = new Obstaculo(625, 319);

    //		generación de kyojines en la pantalla

		kyojines = new Kyojin[5];
		for (int i = 0; i < kyojines.length; i++) {
			kyojines[i] = new Kyojin((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
					(Math.random() * ((entorno.alto() - 100) - 100) + 100), 0.3);

			// para evitar que un kyojin se genere de entrada en la ubicacion de Mikasa

			if (kyojines[i].chocasteConMikasa(mikasa)) {
				kyojines[i] = new Kyojin((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
						(Math.random() * ((entorno.alto() - 100) - 100) + 100), 0.3);
			}

			// para evitar que dos kyojines se generen en el mismo lugar

			for (int j = 0; j < i; j++) {
				if (kyojines[i].chocasteConAlgunOtro(kyojines[j])) {
					kyojines[i] = new Kyojin((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
							(Math.random() * ((entorno.alto() - 100) - 100) + 100), 0.3);
				}
			}

			// para evitar que un kyojin se genere encima de un obstaculo
			for (int k = 0; k < obstaculos.length; k++) {
				if (kyojines[i].chocasteConUnObstaculo(obstaculos[k])) {
					kyojines[i] = new Kyojin((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
							(Math.random() * ((entorno.alto() - 100) - 100) + 100), 0.3);
				}
			}
		}

		kyojinesEnPantalla = kyojines.length;

		this.fondo = Herramientas.cargarImagen("pasto.jpg");
		this.fondoVictoria = Herramientas.cargarImagen("fondo-victoria.jpg");
		this.fondoGameOver = Herramientas.cargarImagen("fondo-game-over.jpg");

		this.entorno.iniciar();

	}

	public void tick() {
		if (kyojinesEnPantalla > 0 && mikasa.getEstaViva()) {
			entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
			mikasa.dibujar(entorno);

			for (Obstaculo o : obstaculos) {
				o.dibujar(entorno);
			}

			for (int i = 0; i < kyojines.length; i++) {
				if (kyojines[i] != null) {
					kyojines[i].dibujar(entorno);
					kyojines[i].moverseHaciaMikasa(mikasa);
					if (kyojines[i].chocasteConEntorno(entorno)) {
						kyojines[i].cambiarDeDireccion();
					}

					for (Obstaculo o : obstaculos) {
						if (kyojines[i].chocasteConUnObstaculo(o)) {
							kyojines[i].detenerseObs(o);
						}
					}
					for (int j = 0; j < i; j++) {
						if (kyojines[j] != null && kyojines[i].chocasteConAlgunOtro(kyojines[j])) {
							kyojines[i].detenerse(kyojines[j]);
							kyojines[j].detenerse(kyojines[i]);
						}
					}
				}
			}

			tiempoDeSuero++;
			tiempoDeJuego++;
			intervaloKyojines++;

			entorno.cambiarFont("Arial", 20, Color.BLACK);
			entorno.escribirTexto("Kyojines eliminados: " + kyojinesEliminados, entorno.ancho() * 0 + 20,
					entorno.alto() - 15);

			if (suero == null && tiempoDeSuero > 640 && !mikasa.getModoKyojin()) { // aprox 10 segundos
				suero = new Suero(Math.random() * ((entorno.ancho() - 50) - 50) + 50,
						50 + (Math.random() * (entorno.alto() - 50)));
				for (Obstaculo o : obstaculos) {
					if (suero.teGenerasteSobreUnObstaculo(o)) {
						suero = null;
					}
				}
			}

			if (suero != null) {
				suero.dibujar(entorno);
			}

			if (tiempoDeJuego % 64 == 0) { // aproximadamente un segundo
				segundos++;
			}

			if (suero != null && !mikasa.getModoKyojin() && mikasa.tomoSuero(suero)) {
				suero = null;
				mikasa.transformarse();
			}

			if (entorno.estaPresionada('a')) {
				mikasa.girarIzquierda();
			}

			if (entorno.estaPresionada('d')) {
				mikasa.girarDerecha();
			}

			if (entorno.estaPresionada('w')) {
				if (mikasa.chocasteConEntorno(entorno)) {
					mikasa.detenerse(entorno);
				}
				for (Obstaculo o : obstaculos) {
					if (mikasa.chocasteConObstaculo(o)) {
						mikasa.detenerseObs(o);
					}
				}
				mikasa.avanzar();
			}

			if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && proyectil == null) {
				proyectil = mikasa.crearProyectil();
			}

			if (proyectil != null) {
				proyectil.dibujar(entorno);
				for (Obstaculo o : obstaculos) {
					if (proyectil.chocasteConObstaculo(o)) {
						proyectil = null;
						return;
					}
				}

				if (proyectil.chocasteCon(entorno)) {
					proyectil = null;
					return;
				}
				proyectil.avanzar();
			}

			for (int i = 0; i < kyojines.length; i++) {
				// muerte de kyojin por choque con proyectil

				if (proyectil != null && kyojines[i] != null && proyectil.chocasteConKyojin(kyojines[i])) {
					kyojines[i] = null;
					kyojinesEliminados++;
					kyojinesEnPantalla--;
					proyectil = null;
					return;
				}
				// muerte de kyojin por choque con mikasa transformada

				if (mikasa.getModoKyojin() && kyojines[i] != null && kyojines[i].chocasteConMikasa(mikasa)) {
					kyojines[i] = null;
					kyojinesEliminados++;
					kyojinesEnPantalla--;
					mikasa.transformarse();
					tiempoDeSuero = 0;
					return;
				}
				// muerte de mikasa en caso de chocar con kyojin en modo normal
				if (kyojines[i] != null && !mikasa.getModoKyojin() && kyojines[i].chocasteConMikasa(mikasa)) {
					mikasa.morirse();
				}

			}

			// regeneracion de kyojines despues de cierto tiempo
			if (intervaloKyojines % 640 == 0) { // chequea la cantidad de kyojines cada aprox 15 segundos
				for (int i = 0; i < kyojines.length; i++) {
					if (kyojines[i] == null) {
						kyojines[i] = new Kyojin((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
								(Math.random() * ((entorno.alto() - 100) - 100) + 100), 0.3);
						kyojinesEnPantalla++;
						for (int j = 0; j < obstaculos.length; j++) {
							if (kyojines[i].chocasteConUnObstaculo(obstaculos[j]) || kyojines[i].chocasteConMikasa(mikasa)) {
								kyojines[i] = null;
								kyojinesEnPantalla--;

							}
						}
					}
				}
			}
		}

		if (kyojinesEnPantalla == 0) {
			victoria();
		}

		if (!mikasa.getEstaViva()) {
			gameOver();
		}

	}
//Pantalla de Victoria 
	private void victoria() {
		entorno.dibujarImagen(fondoVictoria, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
		entorno.cambiarFont("Segoe UI", 50, Color.YELLOW);
		entorno.escribirTexto("¡GANASTE!", entorno.ancho() / 10, entorno.alto() / 6);

		entorno.cambiarFont("Segoe UI", 20, Color.WHITE);
		entorno.escribirTexto("Has eliminado a todos los kyojines.", entorno.ancho() / 10, entorno.alto() / 5 + 20);

		entorno.cambiarFont("Arial", 20, Color.WHITE);
		entorno.escribirTexto("Kyojines eliminados: " + kyojinesEliminados, entorno.ancho() / 10,
				entorno.alto() / 2 - 20);
		entorno.escribirTexto("Tiempo de juego: " + segundos, entorno.ancho() / 10, entorno.alto() / 2 + 20);
	}

//Pantalla de GameOver
	private void gameOver() {
		entorno.dibujarImagen(fondoGameOver, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
		entorno.cambiarFont("Segoe UI", 50, Color.RED);
		entorno.escribirTexto("GAME OVER", entorno.ancho() / 3, entorno.alto() / 6);

		entorno.cambiarFont("Segoe UI", 20, Color.WHITE);
		entorno.escribirTexto("No has podido salvar a Mikasa de las garras de los kyojines.", entorno.ancho() / 4.5,
				entorno.alto() / 5 + 20);

		entorno.cambiarFont("Arial", 20, Color.BLACK);
		entorno.escribirTexto("Kyojines eliminados: " + kyojinesEliminados, entorno.ancho() / 2.5, entorno.alto() - 50);
		entorno.escribirTexto("Tiempo de juego: " + segundos, entorno.ancho() / 2.5, entorno.alto() - 30);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

    }
----

==Conclusiones
* En este informe, tomamos algunas decisiones importantes en el diseño del código, como mantener
funciones separadas en archivos y establecer el nivel de aislamiento externamente para evitar
posibles errores internos. Estas elecciones contribuyeron a una estructura más organizada y fácil
de entender.


* En cuanto al código en Go, implementamos un menú interactivo que facilita la interacción del
usuario con el sistema de gestión de pedidos. La modularidad del código en funciones facilita la
comprensión y el mantenimiento, algo que valoramos mucho como equipo.
La función de generación de correos electrónicos mediante triggers nos pareció una forma inteligente
de mantener a los clientes informados sobre el estado de sus pedidos.


* En general, estamos satisfechos con el resultado. Aprendimos mucho durante el proceso, no solo
técnicamente, sino también en términos de trabajo en equipo y toma de decisiones. Creemos que
hemos logrado construir un sistema funcional y robusto que cumple con los requisitos del trabajo
practico. Fue un trabajo en equipo increíble y nos permitió comprender mejor la importancia y
utilidad de las funciones y triggers en PL/pgSQL para mantener la consistencia y la integridad de los
datos.
