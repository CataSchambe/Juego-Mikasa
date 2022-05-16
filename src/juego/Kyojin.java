package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Kyojin {
	
	private double x;
	private double y;
	private double velocidad;
	private double ancho;
	private double alto;
	private Color color;
	//private Image img;
	
	public Kyojin(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.ancho = 20;
		this.alto = 100;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);	
	}
	
	public void moverseHaciaMikasa() {
		
	}
	
	public boolean chocasteConEntorno() {
		
	}
	
	public boolean chocasteConObstaculo() {
		
	}
}
