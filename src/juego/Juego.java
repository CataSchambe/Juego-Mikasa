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
	private int intervaloKyojines; // intervaloDeReapariciónDeKyojines
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
//		if (perdiste) {
//			// hacés lo de perder
//			return;
//		}
//		
//		if (ganaste) {
//			// hacés lo de ganar
//			return;
//		}
		
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
						kyojines[i].moverseDireccionOpuesta();
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

			if (entorno.sePresiono(entorno.TECLA_ESPACIO) && proyectil == null) {
				proyectil = mikasa.crearProyectil();
			}

			if (proyectil != null) {
				proyectil.dibujar(entorno);
				for (Obstaculo o : obstaculos) {
					if (proyectil.chocasteConObstaculo(o)) {
						proyectil = null;
						break;
					}
				}

				if (proyectil != null && proyectil.chocasteCon(entorno)) {
					proyectil = null;
				}
				
				if (proyectil != null) {
					proyectil.avanzar();
				}
			}

			for (int i = 0; i < kyojines.length; i++) {
				// muerte de kyojin por choque con proyectil
				if (proyectil != null && kyojines[i] != null && proyectil.chocasteConKyojin(kyojines[i])) {
					proyectil = null;
					kyojines[i] = null;
					kyojinesEliminados++;
					kyojinesEnPantalla--;
					return; // break
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
//					mikasa.morirse();
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
							if (kyojines[i].chocasteConUnObstaculo(obstaculos[j])
									|| kyojines[i].chocasteConMikasa(mikasa)) {
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
