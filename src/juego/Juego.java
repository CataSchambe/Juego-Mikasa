package juego;

import entorno.Herramientas;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	private Mikasa mikasa;
	private Kyojin[] variosKyojins;

	private Obstaculo[] obstaculo; // obstaculos
	private Image fondo;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 600);

		this.mikasa = new Mikasa(entorno.ancho() / 2, entorno.alto() / 2, 3, 0);

		obstaculo = new Obstaculo[5];

		// OSTACULOS RANDOM
		for (int i = 0; i < obstaculo.length; i++) {
			double xRandom = Math.random() * ((entorno.ancho() - 50) - 50) + 50;
			double yRandom = Math.random() * ((entorno.alto() - 50) - 50) + 50;
			System.out.println("X: " + xRandom);
			System.out.println("Y: " + yRandom);
			obstaculo[i] = new Obstaculo(xRandom, yRandom); // por algun motivo, a veces se generan valores que exceden
															// lo indicado en el random
															// y debido a eso se generan obstaculos que quedan
															// parcialmente fuera del entorno
			if (obstaculo[i].teGenerasteSobreMikasa(mikasa)) {
				// regenerarse
			}
		}

		// OBSTACULOS FIJOS
//		obstaculo[0] = new Obstaculo (x, y);
//		obstaculo[1] = new Obstaculo (x, y);
//		obstaculo[2] = new Obstaculo (x, y);
//		obstaculo[3] = new Obstaculo (x, y);
//		obstaculo[4] = new Obstaculo (x, y);

//		GENERACION DE KYOJINES EN LA PANTALLA
		variosKyojins = new Kyojin[5];
		for (int i = 0; i < variosKyojins.length; i++) {
			variosKyojins[i] = new Kyojin ((Math.random() * ((entorno.ancho() - 100) - 100) + 100),
					(Math.random() * ((entorno.alto() - 100) - 100) + 100), 1);
			
			for (int j = 0; j < i; j++) {
				if (variosKyojins[i].teGenerasteSobreOtroKyojin(variosKyojins[j])) {
					// regenerarse
				}
			}
			
			for (int k = 0; k < obstaculo.length; k++) {
				if (variosKyojins[i].teGenerasteSobreUnObstaculo(obstaculo[k])) {
					// regenerarse
				}
			}
		}

//		pocion = new Pocion[7];
//		for (int i = 0; i < pocion.length; i++) {
//			pocion[i] = new Pocion(Math.random() * (entorno.ancho() - 0) + 0, (Math.random() * (entorno.alto() - 0)));
//		}

		this.fondo = Herramientas.cargarImagen("pasto.jpg");

		this.entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		mikasa.dibujar(entorno);

//		for (Obstaculo o : obstaculos) {
//			o.dibujar(entorno);
//		}
		
		for (int i = 0; i < obstaculo.length; i++) {
			obstaculo[i].dibujar(entorno);
//			if (obstaculo[i].chocasteConMikasa(mikasa)) {
//				System.out.println("choque con obstaculo");
//			}
		}

//		for (int i = 0; i < pocion.length; i++) {
//			pocion[i].dibujar(entorno);
//			if (pocion[i].chocasteConMikasa(mikasa)) {
//				System.out.println("Mikasa tomo POCION");
//			}
//		}

		// foreach
		for (int i = 0; i < variosKyojins.length; i++) {
			variosKyojins[i].dibujar(entorno);
			variosKyojins[i].moverseHaciaMikasa();
			if (variosKyojins[i].chocasteConEntorno(entorno)) {
				variosKyojins[i].cambiarDeDireccion();
			}
		}

		if (entorno.estaPresionada('a')) {
			mikasa.girarIzquierda();
		}

		if (entorno.estaPresionada('d')) {
			mikasa.girarDerecha();
		}

		if (entorno.estaPresionada('w')) {
			if (mikasa.chocasteConEntorno(entorno) == false) {
				mikasa.avanzar();
			} else {
				mikasa.retroceder();
				System.out.println("choque con entorno/obstaculo");
			}
		}

//		if (kyojin.chocasteConMikasa(mikasa)) {
//			System.out.println("choque con kyojin");
//		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
