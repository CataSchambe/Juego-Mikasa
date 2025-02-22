package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Suero {
	private double x;
	private double y;
	private double tamaño;

	private Image img;

	public Suero(double x, double y) {
		this.x = x;
		this.y = y;
		this.tamaño = 0.5;
		this.img = Herramientas.cargarImagen("suero.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.1);
	}

	public boolean teGenerasteSobreUnObstaculo(Obstaculo obstaculo) {
		return Math.sqrt(Math.pow(x - obstaculo.getX(), 2) + Math.pow(y - obstaculo.getY(), 2)) < tamaño / 2
				+ obstaculo.getTamaño() / 2;
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
