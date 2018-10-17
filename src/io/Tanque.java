package io;

public class Tanque {
	
	double profundidad;
	double base;
	double altCanio;
	double volActual;
	double volumen;
	
	public Tanque() {
		this.profundidad = 0;
		this.base = 0;
		this.altCanio = 0;
		this.volActual = 0;
		this.volumen = 0;
	}
	
	public Tanque(double profundidad, double base, double altCanio, double actual, double vol) {
		this.profundidad = profundidad;
		this.base = base;
		this.altCanio = altCanio;
		this.volActual = actual;
		this.volumen = vol;
	}
	
	
}
