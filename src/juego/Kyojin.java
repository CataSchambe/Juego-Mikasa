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
	private double tamaño;
	private Image img;

	public Kyojin(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.tamaño = 50;
		this.img = Herramientas.cargarImagen("kyojin.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, this.x, this.y, 0, 0.04);
	}

	public void moverseHaciaMikasa(Mikasa mikasa){ 
		if (x < mikasa.getX() && y < mikasa.getY()){ 
			x += velocidad; 
			y += velocidad; 
		} 
		if (x > mikasa.getX() && y < mikasa.getY()){ 
			x -= velocidad; 
			y += velocidad; 
		} 
		if (x < mikasa.getX() && y > mikasa.getY()){ 
			x += velocidad; 
			y -= velocidad; 
		} 
		if (x > mikasa.getX() && y > mikasa.getY()){ 
			x -= velocidad; 
			y -= velocidad; 
		} 		
	}

	public void cambiarDeDireccion() {
		angulo += Math.PI / 2;
	}
	
	public void moverseAlReves() {
		x = x + Math.cos(angulo) * velocidad;
		y = y + Math.sin(angulo) * velocidad;
	}
	
	public boolean chocasteConAlgunOtro(Kyojin kyojin) { // chocasteConOtro()
		return Math.sqrt(Math.pow(x - kyojin.getX(), 2) + Math.pow(y - kyojin.getY(), 2)) < tamaño / 2
				+ kyojin.getTamaño() / 2;
		// (Math.sqrt(Math.pow(Math.abs(x - kyojin.getX()), 2) + Math.pow(Math.abs(y -
		// kyojin.getY()), 2))) < 85;
	}

	public boolean chocasteConUnObstaculo(Obstaculo obstaculo) {
		return Math.sqrt(Math.pow(x - obstaculo.getX(), 2) + Math.pow(y - obstaculo.getY(), 2)) < tamaño / 2
				+ obstaculo.getTamaño() / 2;
	}
	public void detenerseObs(Obstaculo obstaculo) { // FIXME
		if (x < obstaculo.getX() && y < obstaculo.getY()) {
			x -= velocidad;
			y -= velocidad;
		}
		if (x > obstaculo.getX() && y < obstaculo.getY()) {
			x += velocidad;
			y -= velocidad;
		}
		if (x < obstaculo.getX() && y > obstaculo.getY()) {
			x -= velocidad;
			y += velocidad;
		}
		if (x > obstaculo.getX() && y > obstaculo.getY()) {
			x += velocidad;
			y += velocidad;
		}
	}

	public boolean chocasteConMikasa(Mikasa mikasa) {
		return Math.sqrt(Math.pow(x - mikasa.getX(), 2) + Math.pow(y - mikasa.getY(), 2)) < tamaño / 2
				+ mikasa.getTamaño() / 2;
	}

	public boolean chocasteConEntorno(Entorno entorno) {
		return x < tamaño / 2 || x > entorno.ancho() - tamaño / 2 || y < tamaño / 2 || y > entorno.alto() - tamaño / 2;
	}

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
