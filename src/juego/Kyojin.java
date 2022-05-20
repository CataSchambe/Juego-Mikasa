package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Kyojin {

	private double x;
	private double y;

	private double velocidad;
	private double angulo;
	private double tama�o;
	private Color color;
	private Image img;

	public Kyojin(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = -Math.PI / 4;
		this.tama�o = 100;
		this.color = Color.BLUE;
		this.img = Herramientas.cargarImagen("kyojin.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tama�o, color);
		e.dibujarImagen(img, this.x, this.y, 0, 0.10);
	}

	public void moverseHaciaMikasa() {
		x += velocidad * Math.cos(angulo); // prueba del pong
		y += velocidad * Math.sin(angulo);
	}

	public boolean chocasteConEntorno(Entorno entorno) {
		return x < tama�o / 2 || x > entorno.ancho() - tama�o / 2 || y < tama�o / 2 || y > entorno.alto() - tama�o / 2;
	}

	public void cambiarDeDireccion() {
		angulo += Math.PI / 2; // todavia hay que ver que hace si sale del entorno
	}

//	public boolean chocasteConObstaculo() {

//	}

	public boolean chocasteConMikasa(Mikasa mikasa) {
		return x > mikasa.getX() - mikasa.getTama�o() / 2 && x < mikasa.getX() + mikasa.getTama�o() / 2
				&& y + tama�o / 2 > mikasa.getY() - mikasa.getTama�o() / 2
				&& y - tama�o / 2 < mikasa.getY() + mikasa.getTama�o() / 2;
	}

	public double getTama�o() {
		return tama�o;
	}
}
