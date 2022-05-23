package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x;
	private double y;
	private double tamaño;

	private Color color;
	private Image imgEdificio;

	public Obstaculo(double x, double y) {
		this.x = x;
		this.y = y;
		this.tamaño = 25;
		this.color = Color.RED;
		this.imgEdificio = Herramientas.cargarImagen("edificios.png"); // mikasa-derecha.png
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tamaño, color);
//		e.dibujarImagen(imgEdificio, x, y, 0, 0.2); // FIXME
	}

	public boolean teGenerasteSobreMikasa(Mikasa mikasa) {
		return Math.sqrt((x - mikasa.getX()) * (x - mikasa.getX()) + (y - mikasa.getY()) * (y - mikasa.getY())) > tamaño
				/ 2 + mikasa.getTamaño() / 2;
	}

//	public boolean teGenerasteSobreOtroObstaculo(Obstaculo obstaculo) {
//		
//	}

//	public boolean chocasteConMikasa(Mikasa mikasa) {
//		return x > mikasa.getX() - mikasa.getTamaño() / 2 && x < mikasa.getX() + mikasa.getTamaño() / 2
//				&& y + tamaño / 2 > mikasa.getY() - mikasa.getTamaño() / 2
//				&& y - tamaño / 2 < mikasa.getY() + mikasa.getTamaño() / 2;
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
