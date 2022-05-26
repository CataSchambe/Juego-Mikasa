package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {

	private double x;
	private double y;

	private double tamaño;
	//private double velocidad;
	private double angulo;
	//private Color color;
	private Image img;

	public Proyectil(double x, double y, double angulo, double velocidad) {
		this.x = x;
		this.y = y;
		//this.velocidad = velocidad;
		this.angulo = angulo;
		this.tamaño = 25;
		//this.color = Color.GREEN;
		this.img = Herramientas.cargarImagen("proyectil.png");
	}

	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, tamaño, color);
		e.dibujarImagen(img, x, y, angulo, 0.2);
		;
	}

	public void avanzar() {
		x = x + Math.cos(angulo) * 2;
		y = y + Math.sin(angulo) * 2;
	}

	public boolean chocasteCon(Entorno entorno) {
		return x + tamaño > entorno.ancho() || x - tamaño < 0 || y + tamaño > entorno.alto() || y - tamaño < 0;
	}

	public boolean chocasteConObstaculo(Obstaculo obstaculo) {
		return Math.sqrt(Math.pow(x - obstaculo.getX(), 2) + Math.pow(y - obstaculo.getY(), 2)) < tamaño / 2
				+ obstaculo.getTamaño() / 2;
	}

//	
	public boolean chocasteConKyojin(Kyojin kyojin) {
		return (Math.sqrt(
				Math.pow(Math.abs(this.x - kyojin.getX()), 2) + Math.pow(Math.abs(this.y - kyojin.getY()), 2))) < 40;

	}

}
