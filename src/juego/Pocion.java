package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pocion {
	private double x;
	private double y;
	private double tama�o;

	private Color color;
	private Image imgPocion;

	public Pocion(double x, double y) {
		this.x = x;
		this.y = y;
		this.tama�o = 0.5;
		this.color = Color.YELLOW;
		this.imgPocion = Herramientas.cargarImagen("pocion.png"); //
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tama�o, color);
		e.dibujarImagen(imgPocion, x, y, 0, 0.04);
	}

	public boolean chocasteConMikasa(Mikasa mikasa) {
		return x > mikasa.getX() - mikasa.getTama�o() / 2 && x < mikasa.getX() + mikasa.getTama�o() / 2
				&& y + tama�o / 2 > mikasa.getY() - mikasa.getTama�o() / 2
				&& y - tama�o / 2 < mikasa.getY() + mikasa.getTama�o() / 2;
	}

}
