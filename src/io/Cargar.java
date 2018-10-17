package io;

public class Cargar {

	boolean desborda;
	double volDesborde;
	double volAgua;
	Tanque[] vec;
	
	public Cargar(boolean desborda, double desborde, Tanque[] vec, double agua) {
		this.desborda = desborda;
		this.volDesborde = desborde;
		this.vec = vec;
		this.volAgua = agua;
	}

	public Cargar() {
		this.desborda = false;
		this.volDesborde = 0;
		this.vec = null;
		this.volAgua = 0;
	}

	public Cargar resolver() {
		
		double alturaMaxima;
		int tanqueAlturaMaxima = 0;
		int n = this.vec.length, i = 1;
		
		// VERIFICO PARA UN SOLO ESTANQUE
		
		if (n == 1) {
			this.vec[0].volActual = (this.vec[0].base * this.vec[0].profundidad) > this.volAgua ? this.volAgua : this.vec[0].base * this.vec[0].profundidad;
			this.volAgua -= this.vec[0].volActual;
			if (this.volAgua > 0) {
				this.volDesborde = this.volAgua;
				this.desborda = true;
			}
			return this;
		}
		
		// VERIFICO QUE SE REBALSEN
		
		double volTotal = 0;
		for (i=0;i<n;i++) {
			volTotal += this.vec[i].base * this.vec[i].profundidad;
		}
		if (this.volAgua > volTotal) {
			this.desborda = true;
			this.volDesborde = this.volAgua - volTotal;
			return this;
		}
		
		// COMO NO REBALSA, VERIFICO NIVEL DE CADA UNO
		
		i=1;
		alturaMaxima = this.vec[0].altCanio;
		this.vec[0].volActual = (this.vec[0].base * (this.vec[0].profundidad - this.vec[0].altCanio)) > this.volAgua ? this.volAgua : this.vec[0].base * (this.vec[0].profundidad - this.vec[0].altCanio);
		this.volAgua -= this.vec[0].volActual;
				
		while (this.volAgua > 0 && i<n) {
			
			if (this.vec[i].altCanio > this.vec[i-1].altCanio){// LLENO TANQUE ACTUAL HASTA CAÑO DERECHO
				double var = (this.vec[i].profundidad - this.vec[i].altCanio) * this.vec[i].base;
				if(var < this.volAgua)
					this.vec[i].volActual += var;
				else
					this.vec[i].volActual += this.volAgua;
				this.volAgua -= var;
			}
			else if (this.vec[i].altCanio < this.vec[i-1].altCanio && this.vec[i].altCanio >= alturaMaxima) {
				// LLENO EL ACTUAL HASTA EL CAÑO ANTERIOR
				double var = (this.vec[i].profundidad - this.vec[i-1].altCanio) * this.vec[i].base;
				if(var < this.volAgua)
					this.vec[i].volActual += var;
				else
					this.vec[i].volActual += this.volAgua;
				this.volAgua -= var;
				// LLENO DESDE EL SIGUIENTE AL CAÑO MAXIMO HASTA EL ACTUAL, HASTA EL CAÑO DERECHO
				nivelarEstanques(tanqueAlturaMaxima + 1, i, this.vec[i].profundidad - this.vec[i].altCanio);
				if (this.vec[i].altCanio == alturaMaxima) {
					alturaMaxima = this.vec[i].altCanio;
					tanqueAlturaMaxima = i; 
				}
			}	
			else {
				// * LLENO TODOS LOS ANTERIORES *
				// LLENO ACTUAL HASTA CAÑO IZQUIERDO
				double var = (this.vec[i].profundidad - this.vec[i-1].altCanio) * this.vec[i].base;
				if(var < this.volAgua) {
					this.vec[i].volActual += var;
					this.volAgua -= var;
				}
				else {
					this.vec[i].volActual += this.volAgua;
					this.volAgua -= this.vec[i].volActual;
				}
				// NIVELO DESDE MAXIMO ANTERIOR
				nivelarEstanques(tanqueAlturaMaxima + 1, i, this.vec[i].profundidad - this.vec[i].altCanio);
				// NIVELO TODOS LOS ANTERIORES
				nivelarEstanques(0, i, this.vec[i].profundidad - this.vec[i].altCanio);
				alturaMaxima = this.vec[i].altCanio;
				tanqueAlturaMaxima = i;
			}
			i++;
		}
		return this;
	}
	
	
	
	public void nivelarEstanques (int min, int max, double alt) {
		
		/// SOLO NIVELO LOS QUE ESTAN A ESE NIVEL
		
		int i;
		
		/*double nivActual = this.vec[max].profundidad - this.vec[max].volActual / this.vec[max].base;
		i = max - 1;
		while ((this.vec[i].profundidad - this.vec[i].volActual / this.vec[i].base) == nivActual && i > min)
			i--;
		min = i + 1; 	CON ESTO ANDA EL TEST ESE, PERO SE ROMPEN OTROS 4		*/
		
		double h = this.vec[max-1].altCanio - this.vec[max].altCanio;
		double volNecesario = 0, sumaSup = 0;
		//int i;
		
		if (min == max) {
			double vol = (this.vec[min].profundidad - this.vec[min-1].altCanio) * this.vec[min].base;
			if (Double.compare(vol, this.vec[min].volActual) == 0)
				return;
		}
		
		for (i=min;i<=max;i++) {
			volNecesario += this.vec[i].base * h;
			sumaSup += this.vec[i].base;
		}
		
		if (volNecesario < this.volAgua) 
			this.volAgua -= volNecesario;
		else {
			h = this.volAgua / sumaSup;
			this.volAgua = 0;
		}
		
		for (i=min;i<=max;i++)
			this.vec[i].volActual += this.vec[i].base * h;
	
	}
	
}
