package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Kyojin {
	
	private double x;
	private double y;
	private double angulo; 
	private double velocidad;
	private double ancho;
	private double alto;
	private Image img;
	
	public Kyojin(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.ancho = 200;
		this.alto = 20;
		this.angulo = Math.PI / 4;

	}
	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, angulo, 0.28);
	}
}
