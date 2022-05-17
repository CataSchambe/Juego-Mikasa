package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Mikasa {

	private double x;
	private double y;

	private double velocidad;
	private double angulo;

	private double tamaño;
	private Image img;
	private Color color; // esto es solo para el rectangulo que despues se termina ocultando, asi que da
							// igual el color

	public Mikasa(double x, double y, double velocidad, double angulo) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.tamaño = 50;
		this.color = Color.YELLOW;
		this.img = Herramientas.cargarImagen("mikasa.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tamaño, color);
		e.dibujarImagen(img, this.x, this.y, 0, 0.3);
	}

	// cuidado! no usar setters
//	public void girar(double modificador) {
//		angulo = angulo + modificador;
//		if (angulo > Math.PI * 2) {
//			angulo = angulo - Math.PI * 2;
//		}
//		if (angulo < 0) {
//			angulo = angulo + Math.PI * 2;
//		}
//	}

	public void avanzar() {
		x = x + Math.cos(angulo) * 2;
		y = y + Math.sin(angulo) * 2;
	}

	public void girarDerecha() {
		x += velocidad;
	}

	public void girarIzquierda() {
		x -= velocidad;
	}

	public void caminarHaciaArriba() {
		y--;
	}

	public void caminarHaciaAbajo() {
		y++;
	}

	public boolean chocasteConEntorno(Entorno entorno) {
		return x < tamaño / 2 || x > entorno.ancho() - tamaño / 2 || y < tamaño / 2 || y > entorno.alto() - tamaño / 2;
	}

	public boolean chocasteConObstaculo(Obstaculo obstaculo) {
		return x > obstaculo.getX() - obstaculo.getAncho() / 2 && x < obstaculo.getX() + obstaculo.getAncho() / 2
				&& y + tamaño / 2 > obstaculo.getY() - obstaculo.getAlto() / 2;
	}

	public void detener() {

	}

	public double getTamaño() {
		return tamaño;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
