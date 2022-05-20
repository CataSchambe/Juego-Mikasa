package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x;
	private double y;
	private double tama�o;

	private Color color;
	private Image imgEdificio;
//	private Image imgArbol; 
//	private Image imgSuero; 	

	public Obstaculo(double x, double y) {
		this.x = x;
		this.y = y;
		this.tama�o = 40;
		this.color = Color.RED;
		this.imgEdificio = Herramientas.cargarImagen("edificios.png"); // mikasa-derecha.png
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tama�o, color);
		e.dibujarImagen(imgEdificio, x, y, 0, 0.2); // FIXME
	}

	public boolean chocasteConMikasa(Mikasa mikasa) {
		return x > mikasa.getX() - mikasa.getTama�o() / 2 && x < mikasa.getX() + mikasa.getTama�o() / 2
				&& y + tama�o / 2 > mikasa.getY() - mikasa.getTama�o() / 2
				&& y - tama�o / 2 < mikasa.getY() + mikasa.getTama�o() / 2;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getTama�o() {
		return tama�o;
	}

}
