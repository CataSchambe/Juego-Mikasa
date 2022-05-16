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
	private double ancho;
	private double alto;
	private Image img;
	private Color color;      // esto es solo para el rectangulo que despues se termina ocultando, asi que da igual el color

	public Mikasa(double x, double y, double velocidad, double angulo) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.ancho = 25;
		this.alto = 115;
		this.color = Color.YELLOW;
		this.img = Herramientas.cargarImagen("mikasa (1).png");
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.dibujarImagen(img, this.x, this.y, 0, 0.3);                //ultimo cambio (para que tenga imagen mikasa)
	}

	public void girar(double modificador) {
		angulo = angulo + modificador;
		if (angulo > Math.PI * 2) {
			angulo = angulo - Math.PI * 2;
		}
		if (angulo < 0) {
			angulo = angulo + Math.PI * 2;
		}
	}

	public void avanzar() {
		x = x + Math.cos(angulo) * 2;
		y = y + Math.sin(angulo) * 2;
	}
	
	public boolean chocasteConEntorno(Entorno entorno) {
		return x-ancho/2 < entorno.ancho() - entorno.ancho() || x+ancho/2 > entorno.ancho() || y-alto/2 < entorno.alto() - entorno.alto() || y+alto/2 > entorno.alto();
	} 
	
	public boolean chocasteConObstaculo(Obstaculo obstaculo) {
		return x-ancho/2 < obstaculo.getAncho() - obstaculo.getAncho() || x+ancho/2 > obstaculo.getAncho() || y-alto/2 < obstaculo.getAlto() - obstaculo.getAlto() || y+alto/2 > obstaculo.getAlto();
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
