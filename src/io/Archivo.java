package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Archivo {

	public static Cargar leerArch(String path) throws FileNotFoundException {
		FileReader fr = new FileReader(path);
		Scanner sc = new Scanner(fr);
		int n = sc.nextInt(), i;
		Cargar res = new Cargar();
		res.vec = new Tanque[n];
		for (i=0;i<n-1;i++) {
			res.vec[i] = new Tanque();
			res.vec[i].base = sc.nextDouble();
			res.vec[i].profundidad = sc.nextDouble();
			res.vec[i].volumen = res.vec[i].base * res.vec[i].profundidad;
			res.vec[i].altCanio = sc.nextDouble();
		}
		res.vec[i] = new Tanque();
		res.vec[i].base = sc.nextDouble();
		res.vec[i].profundidad = sc.nextDouble();
		res.vec[i].volumen = res.vec[i].base * res.vec[i].profundidad;
		res.vec[i].altCanio = 0;
		res.volAgua = sc.nextDouble();
		sc.close();
		return res;			
	}
	
	public static void escribir (Cargar carga, String path) throws IOException {
		
		FileWriter fw = new FileWriter(path);
		PrintWriter pw = new PrintWriter(fw);		
		
		if (carga.desborda) {
			pw.print("Hay desborde: " + carga.volDesborde);
		}
		else {
			int c = 0, n=carga.vec.length;
			for (Tanque t:carga.vec) {
				if (t.volActual != 0)
					c++;
			}
			pw.println(c);
			for (int i = 0;i<n;i++) {
				if (carga.vec[i].volActual != 0) {
					int h = (int) carga.vec[i].volActual / (int) carga.vec[i].base;
					 pw.println( (i+1) + " " + h);
				}
			}
		}
		pw.close();
	}
	
}
