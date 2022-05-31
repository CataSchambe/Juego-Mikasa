package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Mikasa {

	private double x;
	private double y;

	private double velocidad;
	private double angulo;

	private double tamaño;

	private boolean modoKyojin;

	private Image imgMikasa;
	private Image imgTransformada;

	public Mikasa(double x, double y, double velocidad, double angulo) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.tamaño = 50;
		this.imgMikasa = Herramientas.cargarImagen("mikasa.png");
		this.imgTransformada = Herramientas.cargarImagen("transformada.png");
		this.modoKyojin = false;
	}

	public void dibujar(Entorno entorno) {
		if (!modoKyojin) {
			entorno.dibujarImagen(imgMikasa, x, y, angulo, 0.20);
		} else {
			entorno.dibujarImagen(imgTransformada, x, y, angulo, 0.28);
		}

	}

	public void avanzar() {
		x = x + Math.cos(angulo) * velocidad;
		y = y + Math.sin(angulo) * velocidad;
	}

	public void detenerse(Entorno entorno) {
		if (chocasteLadoIzquierdo(entorno)) {
			x = tamaño / 2;
		}
		if (chocasteLadoDerecho(entorno)) {
			x = entorno.ancho() - tamaño / 2;
		}
		if (chocasteLadoSuperior(entorno)) {
			y = tamaño / 2;
		}
		if (chocasteLadoInferior(entorno)) {
			y = entorno.alto() - tamaño / 2;
		}
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

	public void girarDerecha() {
		angulo += 0.05;
	}

	public void girarIzquierda() {
		angulo -= 0.05;
	}

	public void transformarse() {
		modoKyojin = !modoKyojin;
	}

	public Proyectil crearProyectil() {
		return new Proyectil(x, y, angulo, 5);
	}

	public boolean chocasteConEntorno(Entorno e) {
		return chocasteLadoIzquierdo(e) || chocasteLadoDerecho(e) || chocasteLadoSuperior(e) || chocasteLadoInferior(e);
	}

	public boolean chocasteLadoIzquierdo(Entorno entorno) {
		return x - tamaño / 2 < entorno.ancho() * 0;
	}

	public boolean chocasteLadoDerecho(Entorno entorno) {
		return x + tamaño / 2 > entorno.ancho();
	}

	public boolean chocasteLadoSuperior(Entorno entorno) {
		return y - tamaño / 2 < entorno.alto() * 0;
	}

	public boolean chocasteLadoInferior(Entorno entorno) {
		return y + tamaño / 2 > entorno.alto();
	}

	public boolean chocasteConObstaculo(Obstaculo obstaculo) {
		return Math.sqrt(Math.pow(x - obstaculo.getX(), 2) + Math.pow(y - obstaculo.getY(), 2)) < tamaño / 2
				+ obstaculo.getTamaño() / 2;
	}

	public boolean chocasteConKyojin(Kyojin kyojin) {
		return (Math.sqrt(
				Math.pow(Math.abs(this.x - kyojin.getX()), 2) + Math.pow(Math.abs(this.y - kyojin.getY()), 2))) < 40;

	}

	public boolean tomoSuero(Suero suero) {
		return Math.sqrt(Math.pow(x - suero.getX(), 2) + Math.pow(y - suero.getY(), 2)) < tamaño / 2
				+ suero.getTamaño() / 2;
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

	public boolean estasEnModoKyojin() {
		return modoKyojin;
	}

}
