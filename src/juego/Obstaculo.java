package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Color color;
	private Image img;
	// despues va a hacer falta poner varias imagenes, como casas, arboles, etc.
	
	public Obstaculo (double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 70;
		this.alto = 20;
		this.color = Color.RED;
		this.img = Herramientas.cargarImagen("edificios.png");
	}
	
	public void dibujar (Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(img, x, y, 0, 0.2); //FIXME
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
}
