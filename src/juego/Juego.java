package juego;

import entorno.Herramientas;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;
	private Mikasa mikasa; 
	private Kyojin kyojin;
	private Image fondo;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 600);
		this.mikasa = new Mikasa(entorno.ancho() / 2, entorno.alto() / 2, 3);
		this.kyojin = new Kyojin(entorno.ancho() / 2, entorno.alto() - 15, 2);
		this.fondo = Herramientas.cargarImagen("pasto.jpg");
		this.entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);
		mikasa.dibujar(entorno);
		kyojin.dibujar(entorno);
		
		if (entorno.estaPresionada('a')) {
			mikasa.moverHaciaIzquierda();
			//mikasa.acelerar();    SI USAMOS ACELERAR
		}
		if (entorno.estaPresionada('d')) {
			mikasa.moverHaciaDerecha();
			//mikasa.acelerar();    SI USAMOS ACELERAR
		}

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
