package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {

	private double x;
	private double y;
	private double tamaño;

	//private Color color;
	private Image img;

	public Obstaculo(double x, double y) {
		this.x = x;
		this.y = y;
		this.tamaño = 35;
		//this.color = Color.RED;
		this.img = Herramientas.cargarImagen("edificios.png"); // mikasa-derecha.png
	}

	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, tamaño, color);
		e.dibujarImagen(img, x, y, 0, 0.2); // FIXME
	}

//	public boolean teGenerasteSobreOtroObstaculo(Obstaculo obstaculo) {
//		
//	}

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
