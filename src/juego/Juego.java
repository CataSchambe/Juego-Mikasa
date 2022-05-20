package juego;

import entorno.Herramientas;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	private Mikasa mikasa;
	private Kyojin kyojin;
	private Kyojin[] variosKyojins;

	private Obstaculo[] obstaculo;
	private Pocion[] pocion;
	private Image fondo;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 700);
		// despues ponerlo en 800 x 600

		this.mikasa = new Mikasa(entorno.ancho() / 2, entorno.alto() / 2, 3, 0);

		this.kyojin = new Kyojin(entorno.ancho() / 3, entorno.alto() / 3, 2);
		variosKyojins = new Kyojin[5];
		for (int i = 0; i < variosKyojins.length; i++) {
			variosKyojins[i] = new Kyojin(Math.random() * entorno.ancho() - 15, entorno.alto() - 100, 2);
		}

		obstaculo = new Obstaculo[5];
		for (int i = 0; i < obstaculo.length; i++) {
			obstaculo[i] = new Obstaculo(Math.random() * (entorno.ancho() - 0) + 0,
					(Math.random() * (entorno.alto() - 0)));
		}
		// this.obstaculo = new Obstaculo(Math.random() * (entorno.ancho() - 0) + 0,
		// Math.random() * (entorno.alto() - 0));

		pocion = new Pocion[7];
		for (int i = 0; i < pocion.length; i++) {
			pocion[i] = new Pocion(Math.random() * (entorno.ancho() - 0) + 0, (Math.random() * (entorno.alto() - 0)));
		}

		this.fondo = Herramientas.cargarImagen("pasto.jpg");

		this.entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		mikasa.dibujar(entorno);

		for (int i = 0; i < obstaculo.length; i++) {
			obstaculo[i].dibujar(entorno);
			if (obstaculo[i].chocasteConMikasa(mikasa)) {
				System.out.println("choque con obstaculo");
			}
		}

		for (int i = 0; i < pocion.length; i++) {
			pocion[i].dibujar(entorno);
			if (pocion[i].chocasteConMikasa(mikasa)) {
				System.out.println("Mikasa tomo POCION");
			}
		}

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
			mikasa.avanzar();

			if (mikasa.chocasteConEntorno(entorno)) {
				System.out.println("Choque");
				mikasa.detener();
				// Pensar que hacer ante colisiones
			}

			if (kyojin.chocasteConMikasa(mikasa)) {
				System.out.println("choque con kyojin");
			}
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
