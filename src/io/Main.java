package io;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Cargar carga = Archivo.leerArch("estanques.in");
		carga.resolver();
		Archivo.escribir(carga, "estanques.out");
	}
	
}
