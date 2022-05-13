package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Personaje {

	private double x;
	private double y;
	private double angulo;
	private Image img1; // img, imgUp, imgLeft, imagenMirandoIzquierda

	public Personaje(int x, int y) {
		this.x = x;
		this.y = y;

		img1 = Herramientas.cargarImagen("nave.png"); // consistencia

	}

	public void dibujarse(Entorno entorno) {
//			entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
	}

	// muy fulero!!! es un setter
	public void girar(double modificador) {
		this.angulo = this.angulo + modificador;
		if (this.angulo > Math.PI * 2) {
			this.angulo = this.angulo - Math.PI * 2;
		}
		if (this.angulo < 0) {
			this.angulo = this.angulo + Math.PI * 2;
		}
	}

	public void moverAdelante() {
		this.x += Math.cos(this.angulo) * 2;
		this.y += Math.sin(this.angulo) * 2;
		if (this.x > 860) {
			this.x = -60;
		}
		if (this.x < -60) {
			this.x = 860;
		}
		if (this.y > 660) {
			this.y = -60;
		}
		if (this.y < -60) {
			this.y = 660;
		}
	}

	

}
