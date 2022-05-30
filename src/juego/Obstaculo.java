package juego;

import java.awt.Color;
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
		this.tamaño = 35;
<<<<<<< HEAD
		this.color = Color.RED;
		this.img = Herramientas.cargarImagen("edificios.png");
=======
		this.img = Herramientas.cargarImagen("arboles.png"); 
>>>>>>> d0c57f9ca7f637e87918821b6e5e32f519fed023
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 0.2); // FIXME
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
