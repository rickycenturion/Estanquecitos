package io;

import org.junit.Assert;
import org.junit.Test;

public class Tests {
	
	@Test
	public void testNivelarEstanques() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[4];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 2;
		estanque1.profundidad = 6;
		estanque1.altCanio = 4;
		estanque1.volActual = 6;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 2;
		estanque2.profundidad = 5;
		estanque2.altCanio = 3;
		estanque2.volActual = 4;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 4;
		estanque3.altCanio = 2;
		estanque3.volActual = 2;
		
		Tanque estanque4 = new Tanque();
		estanque4.base = 2;
		estanque4.profundidad = 3;
		
		obj.volAgua = 8;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.vec[3] = estanque4;
		obj.nivelarEstanques(0, 2, 2);
		
		Assert.assertEquals(8, obj.vec[0].volActual, 0);
		Assert.assertEquals(6, obj.vec[1].volActual, 0);
		Assert.assertEquals(4, obj.vec[2].volActual, 0);
		Assert.assertEquals(0, obj.vec[3].volActual, 0);
		Assert.assertEquals(2, obj.volAgua, 0);
	}

	@Test
	public void testEstanqueUnicoSinRebalsar() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[1];
		
		Tanque estanque = new Tanque();
		estanque.base = 3;
		estanque.profundidad = 5;
		
		obj.volAgua = 12;
		obj.vec[0] = estanque;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(12, obj.vec[0].volActual, 0);
	}
	
	@Test
	public void testEstanqueUnicoQueRebalsa() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[1];
		
		Tanque estanque = new Tanque();
		estanque.base = 3;
		estanque.profundidad = 5;
		
		obj.volAgua = 18;
		obj.vec[0] = estanque;
		obj.resolver();
		
		Assert.assertEquals(true, obj.desborda);
		Assert.assertEquals(3, obj.volDesborde, 0);
	}
	
	@Test
	public void testDosEstanquesNoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[2];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 5;
		estanque1.altCanio = 1;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 4;
		estanque2.profundidad = 4;
		
		obj.volAgua = 16;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(12, obj.vec[0].volActual, 0);
		Assert.assertEquals(4, obj.vec[1].volActual, 0);
	}
	
	@Test
	public void testDosEstanquesLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[2];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 5;
		estanque1.altCanio = 1;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 4;
		estanque2.profundidad = 6;
		
		obj.volAgua = 40;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.resolver();
		
		Assert.assertEquals(true, obj.desborda);
		Assert.assertEquals(1, obj.volDesborde, 0);
	}
	
	@Test
	public void testTresEstanquesHaciaArribaNoLLenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[3];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 5;
		estanque1.altCanio = 3;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 3;
		estanque2.profundidad = 5;
		estanque2.altCanio = 1;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 4;
		estanque3.profundidad = 2;
		
		obj.volAgua = 28;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(12, obj.vec[0].volActual, 0);
		Assert.assertEquals(12, obj.vec[1].volActual, 0);
		Assert.assertEquals(4, obj.vec[2].volActual, 0);
	}
	
	@Test
	public void testTresEstanquesHaciaArribaLLenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[3];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 5;
		estanque1.altCanio = 3;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 3;
		estanque2.profundidad = 5;
		estanque2.altCanio = 1;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 4;
		estanque3.profundidad = 2;
		
		obj.volAgua = 40;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.resolver();
		
		Assert.assertEquals(true, obj.desborda);
		Assert.assertEquals(2, obj.volDesborde, 0);
	}
	
	@Test
	public void testTresEstanquesHaciaAbajoNoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[3];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 3;
		estanque1.altCanio = 2;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 2;
		estanque2.profundidad = 4;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 5;
		
		obj.volAgua = 9;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(3, obj.vec[0].volActual, 0);
		Assert.assertEquals(2, obj.vec[1].volActual, 0);
		Assert.assertEquals(4, obj.vec[2].volActual, 0);
	}
	
	@Test
	public void testTresEstanquesHaciaAbajoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[3];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 3;
		estanque1.altCanio = 2;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 2;
		estanque2.profundidad = 4;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 5;
		
		obj.volAgua = 30;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.resolver();
		
		Assert.assertEquals(true, obj.desborda);
		Assert.assertEquals(3, obj.volDesborde, 0);
	}
	
	@Test
	public void testCuatroEstanquesHaciaAbajoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[4];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 3;
		estanque1.altCanio = 2;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 3;
		estanque2.profundidad = 4;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 5;
		estanque3.altCanio = 4;
		
		Tanque estanque4 = new Tanque();
		estanque4.base = 2;
		estanque4.profundidad = 6;
		
		obj.volAgua = 45;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.vec[3] = estanque4;
		obj.resolver();
		
		Assert.assertEquals(true, obj.desborda);
		Assert.assertEquals(2, obj.volDesborde, 0);
	}
	
	@Test
	public void testCuatroEstanquesHaciaAbajoNoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[4];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 3;
		estanque1.altCanio = 2;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 3;
		estanque2.profundidad = 4;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 5;
		estanque3.altCanio = 4;
		
		Tanque estanque4 = new Tanque();
		estanque4.base = 2;
		estanque4.profundidad = 6;
		
		obj.volAgua = 16;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.vec[3] = estanque4;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(3, obj.vec[0].volActual, 0);
		Assert.assertEquals(3, obj.vec[1].volActual, 0);
		Assert.assertEquals(4, obj.vec[2].volActual, 0);
		Assert.assertEquals(6, obj.vec[3].volActual, 0);
	}
	
	@Test
	public void testCuatroEstanquesHaciaArribaLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[4];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 2;
		estanque1.profundidad = 6;
		estanque1.altCanio = 4;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 2;
		estanque2.profundidad = 5;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 4;
		estanque3.altCanio = 2;
		
		Tanque estanque4 = new Tanque();
		estanque4.base = 2;
		estanque4.profundidad = 3;
		
		obj.volAgua = 38;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.vec[3] = estanque4;
		obj.resolver();
		
		Assert.assertEquals(true, obj.desborda);
		Assert.assertEquals(2, obj.volDesborde, 0);
	}
	
	@Test
	public void testCuatroEstanquesHaciaArribaNoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[4];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 2;
		estanque1.profundidad = 6;
		estanque1.altCanio = 4;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 2;
		estanque2.profundidad = 5;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 4;
		estanque3.altCanio = 2;
		
		Tanque estanque4 = new Tanque();
		estanque4.base = 2;
		estanque4.profundidad = 3;
		
		obj.volAgua = 28;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.vec[3] = estanque4;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(10, obj.vec[0].volActual, 0);
		Assert.assertEquals(8, obj.vec[1].volActual, 0);
		Assert.assertEquals(6, obj.vec[2].volActual, 0);
		Assert.assertEquals(4, obj.vec[3].volActual, 0);
	}
	
	@Test
	public void testCuatroEstanquesMixtosNoLlenos() {
		Cargar obj = new Cargar();
		obj.vec = new Tanque[4];
		
		Tanque estanque1 = new Tanque();
		estanque1.base = 3;
		estanque1.profundidad = 4;
		estanque1.altCanio = 1;
		
		Tanque estanque2 = new Tanque();
		estanque2.base = 3;
		estanque2.profundidad = 4;
		estanque2.altCanio = 3;
		
		Tanque estanque3 = new Tanque();
		estanque3.base = 2;
		estanque3.profundidad = 4;
		estanque3.altCanio = 2;
		
		Tanque estanque4 = new Tanque();
		estanque4.base = 2;
		estanque4.profundidad = 3;
		
		obj.volAgua = 21;
		obj.vec[0] = estanque1;
		obj.vec[1] = estanque2;
		obj.vec[2] = estanque3;
		obj.vec[3] = estanque4;
		obj.resolver();
		
		Assert.assertEquals(false, obj.desborda);
		Assert.assertEquals(0, obj.volDesborde, 0);
		Assert.assertEquals(9, obj.vec[0].volActual, 0);
		Assert.assertEquals(6, obj.vec[1].volActual, 0);
		Assert.assertEquals(4, obj.vec[2].volActual, 0);
		Assert.assertEquals(2, obj.vec[3].volActual, 0);
	}
	
}
