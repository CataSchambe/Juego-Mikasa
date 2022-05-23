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

	private double tama�o;
	private Image img;
	private Color color; // esto es solo para el rectangulo que despues se termina ocultando, asi que da
							// igual el color

	public Mikasa(double x, double y, double velocidad, double angulo) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.tama�o = 50;
		this.color = Color.YELLOW;
		this.img = Herramientas.cargarImagen("mikasa.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tama�o, color);
		e.dibujarImagen(img, this.x, this.y, angulo, 0.3);
	}

	public void avanzar() {
		x = x + Math.cos(angulo) * 2;
		y = y + Math.sin(angulo) * 2;
		System.out.println("Valor actual del �ngulo: " + angulo); // para ir chequeando manualmente el angulo, cuando
																	// estemos seguros de que anda todo bien, borrar
	}

	public void retroceder() { // este m�todo es para que si Mikasa choca con el entorno/obstaculo que
								// retroceda un par de pixeles, sino se queda atrapada ya que en la clase juego
								// no puede avanzar
		x = x - Math.cos(angulo) * 2;
		y = y - Math.sin(angulo) * 2;
	}

	public void girarDerecha() {
		angulo = angulo + Math.PI / 45;
		if (angulo > Math.PI * 2) {
			angulo = angulo - Math.PI * 2;
		}
		if (angulo < 0) {
			angulo = angulo + Math.PI * 2;
		}
	}

	public void girarIzquierda() {
		angulo = angulo - Math.PI / 45;
		if (angulo > Math.PI * 2) {
			angulo = angulo - Math.PI * 2;
		}
		if (angulo < 0) {
			angulo = angulo + Math.PI * 2;
		}
	}

	public boolean chocasteConEntorno(Entorno entorno) { // consultar si en ancho-ancho y alto-alto directamente se
															// puede poner 0
		return x < entorno.ancho() - entorno.ancho() + tama�o / 2 || x > entorno.ancho() - tama�o / 2
				|| y < entorno.alto() - entorno.alto() + tama�o / 2 || y > entorno.alto() - tama�o / 2;
	}

	public double getTama�o() {
		return tama�o;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
