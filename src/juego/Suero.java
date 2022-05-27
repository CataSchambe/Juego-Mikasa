package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Suero {
	private double x;
	private double y;
	private double tamaño;

	private Color color;
	private Image imgPocion;

	public Suero(double x, double y) {
		this.x = x;
		this.y = y;
		this.tamaño = 0.5;
		this.color = Color.YELLOW;
		this.imgPocion = Herramientas.cargarImagen("suero.png"); //
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tamaño, color);
		e.dibujarImagen(imgPocion, x, y, 0, 0.1);
	}

}
