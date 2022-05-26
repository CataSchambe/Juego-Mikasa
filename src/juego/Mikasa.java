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
		e.dibujarImagen(img, this.x, this.y, angulo, 0.2);
	}

	public void avanzar() {
		x = x + Math.cos(angulo) * 2;
		y = y + Math.sin(angulo) * 2;
	}

	// este método es para que si Mikasa choca con el entorno/obstaculo que
	// retroceda un par de pixeles, sino se queda atrapada ya que en la clase juego
	// no puede avanzar
	public void retroceder() {
		x = x - Math.cos(angulo) * 2;
		y = y - Math.sin(angulo) * 2;
	}

	public void girarDerecha() {
		angulo += 0.05;
//		System.out.println("angulo: " + angulo);
	}

	public void girarIzquierda() {
		angulo -= 0.05;
//		System.out.println("angulo: " + angulo);
	}

	public boolean chocasteConEntorno(Entorno entorno) { // consultar si en ancho-ancho y alto-alto directamente se
															// puede poner 0
		return x < entorno.ancho() - entorno.ancho() + tamaño / 2 || x > entorno.ancho() - tamaño / 2
				|| y < entorno.alto() - entorno.alto() + tamaño / 2 || y > entorno.alto() - tamaño / 2;
	}

	public boolean chocasteConObstaculo(Obstaculo obstaculo) {
		return Math.sqrt(Math.pow(x - obstaculo.getX(), 2) + Math.pow(y - obstaculo.getY(), 2)) < tamaño / 2
				+ obstaculo.getTamaño() / 2;
	}

	public double getTamaño() {
		return tamaño;
	}

	public double getAngulo() {
		return angulo;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Proyectil crearProyectil() {
		return new Proyectil(x, y, angulo, velocidad);
	}

}
