package juego;

import entorno.Herramientas;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	private Mikasa mikasa;
	private Kyojin kyojin;
	private Obstaculo obstaculo;
	private Image fondo;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 700);
		// despues ponerlo en 800 x 600
		this.mikasa = new Mikasa(entorno.ancho() / 2, entorno.alto() / 2, 3, 0);
		this.kyojin = new Kyojin(entorno.ancho() / 2, entorno.alto() - 15, 2);
		this.obstaculo = new Obstaculo(Math.random() * (entorno.ancho() - 0) + 0, Math.random() * (entorno.alto() - 0));
		this.fondo = Herramientas.cargarImagen("pasto.jpg");
		this.entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		mikasa.dibujar(entorno);
		kyojin.dibujar(entorno);
		obstaculo.dibujar(entorno);

		if (entorno.estaPresionada('a')) {
			mikasa.girarIzquierda();
		}

		if (entorno.estaPresionada('d')) {
			mikasa.girarDerecha();
		}

		if (entorno.estaPresionada('w')) {
			mikasa.avanzar();
			// mikasa.acelerar(); SI USAMOS ACELERAR
		}

		if (mikasa.chocasteConEntorno(entorno)) {
			System.out.println("Choque");
//			mikasa.detener(); // Pensar que hacer ante colisiones
		}

		if (mikasa.chocasteConObstaculo(obstaculo)) {
			System.out.println("choque con obstaculo");
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
