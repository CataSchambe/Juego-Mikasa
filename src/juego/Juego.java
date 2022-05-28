package juego;

import entorno.Herramientas;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	private Mikasa mikasa;
	private Kyojin[] kyojines;

	private Obstaculo[] obstaculos;
	private Image fondo;
	private Suero suero;
	private Proyectil proyectil;

	private int tiempo = 0;
	private int tiempoVuelta = 0;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 600);
		this.mikasa = new Mikasa(entorno.ancho() / 2, entorno.alto() / 2, 2, 0);

		// generaciÃ³n de obstaculos (fijos)
		obstaculos = new Obstaculo[5];

		obstaculos[0] = new Obstaculo(115, 397);
		obstaculos[1] = new Obstaculo(427, 121);
		obstaculos[2] = new Obstaculo(700, 520);
		obstaculos[3] = new Obstaculo(178, 106);
		obstaculos[4] = new Obstaculo(625, 319);

		suero = new Suero(Math.random() * (entorno.ancho() - 0) + 0, (Math.random() * (entorno.alto() - 0)));

//		generaciÃ³n de kyojines en la pantalla
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

		this.fondo = Herramientas.cargarImagen("pasto.jpg");

		this.entorno.iniciar();

	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		mikasa.dibujar(entorno);
		suero.dibujar(entorno);

		for (Obstaculo o : obstaculos) {
			o.dibujar(entorno);
		}

		for (Kyojin k : kyojines) {
			k.dibujar(entorno);
			k.moverseHaciaMikasa(mikasa);
			if (k.chocasteConEntorno(entorno)) {
				k.cambiarDeDireccion();
			}
			for (Obstaculo o : obstaculos) {
				if (k.chocasteConUnObstaculo(o)) {
					k.cambiarDeDireccion();

				}
			}
		}

		// choque entre kyojines, esto no se puede meter en el foreach porque cada
		// kyojin se compara con si mismo
		for (int i = 0; i < kyojines.length; i++) {
			for (int j = 0; j < i; j++) {
				if (kyojines[i].chocasteConAlgunOtro(kyojines[j])) {
					kyojines[i].cambiarDeDireccion();
					kyojines[j].cambiarDeDireccion();
				}
			}
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

//		if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
////			Proyectil proyectil = new Proyectil(mikasa.getX(), mikasa.getY(), mikasa.getAngulo(), 1);
////			proyectil.dibujar(entorno);
////			proyectil.avanzar();
//		}
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO) && proyectil == null) {
			proyectil = new Proyectil(mikasa.getX(), mikasa.getY(), mikasa.getAngulo(), 1);
			proyectil.dibujar(entorno);

		}
		if (proyectil != null) {
			proyectil.dibujar(entorno);
//			for (Obstaculo o : obstaculos) {
//				if (proyectil.chocasteConObstaculo(o)) {
//					proyectil = null;
//				}
//			}
			proyectil.avanzar();

			if (proyectil.chocasteCon(entorno)) {
				proyectil = null;
			}

		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
