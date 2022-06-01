package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {

	private double x;
	private double y;
	private double tamaño;

	private Image img;

	public Obstaculo(double x, double y) {
		this.x = x;
		this.y = y;
		this.tamaño = 60;
		this.img = Herramientas.cargarImagen("arboles.png");

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.2);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getTamaño() {
		return tamaño;
	}

}
