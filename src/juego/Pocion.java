package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pocion {
	private double x;
	private double y;
	private double tamaño;

	private Color color;
	private Image imgPocion;

	public Pocion(double x, double y) {
		this.x = x;
		this.y = y;
		this.tamaño = 0.5;
		this.color = Color.YELLOW;
		this.imgPocion = Herramientas.cargarImagen("pocion.png"); //
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tamaño, color);
		e.dibujarImagen(imgPocion, x, y, 0, 0.04);
	}

	// no lo compro
	public boolean chocasteConMikasa(Mikasa mikasa) {
		return x > mikasa.getX() - mikasa.getTamaño() / 2 && x < mikasa.getX() + mikasa.getTamaño() / 2
				&& y + tamaño / 2 > mikasa.getY() - mikasa.getTamaño() / 2
				&& y - tamaño / 2 < mikasa.getY() + mikasa.getTamaño() / 2;
	}

}
