package juego;

import entorno.Herramientas;
import java.awt.Image;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Personaje mikasa; // respeten los nombres del coso
	private Kyojin kyojin;
	private Image fondo;
	private int puntaje;

	public Juego() {
		// Inicializa el objeto entorno
		entorno = new Entorno(this, "Attack on Titan - Grupo 9", 800, 600);

		kyojin = new Kyojin(entorno.ancho() / 2, entorno.alto() - 15, 2);
		// Inicializar lo que haga falta para el juego
		// ...
		// Inicia el juego
		fondo = Herramientas.cargarImagen("pasto.jpg");
		entorno.iniciar();
	}

	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
