package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Mikasa {
	private double x;
	private double y;
	private double velocidad;
	private double ancho;
	private double alto;
	private Image img;
	private Color color;

	public Mikasa(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.ancho = 10;
		this.alto = 10;
		this.color = Color.YELLOW;
		this.img = Herramientas.cargarImagen("mikasa (1).png");
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(img, this.x, this.y, 0);                //ultimo cambio (para que tenga imagen mikasa)
	}

	public void moverHaciaIzquierda() {
		x -= velocidad;
	}

	public void moverHaciaDerecha() {
		x += velocidad;
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
