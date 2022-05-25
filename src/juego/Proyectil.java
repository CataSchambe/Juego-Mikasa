package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	
	private double x;
	private double y;
	
	private double tamaño;
	private double velocidad;
	private double angulo;
	private Color color;
	private Image img;

	public Proyectil(double x, double y, double angulo, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.tamaño = 20;
		this.color = Color.GREEN;
		this.img = Herramientas.cargarImagen("proyectil.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, tamaño, color);
		e.dibujarImagen(img, x, y, angulo);
	}

	public void avanzar() {
		x = x + Math.cos(angulo) * 2;
		y = y + Math.sin(angulo) * 2;
	}

//	public boolean chocasteConObstaculo() {
//		
//	}
//	
//	public boolean chocasteConKyojin() {
//		
//	}
//	
//	public boolean chocasteConEntorno() {
//		
//	}

}
