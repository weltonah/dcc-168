
import static org.junit.Assert.*;

import org.junit.Test;

public class CoberturaCalc6 {


	@Test
	public void test5() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 3f , colorir.calculadora('/' , 6 , 2) , 0);
	}
	
	@Test
	public void test4() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 0f , colorir.calculadora('/' , 2 , 0) , 0);	
	}
	
	@Test
	public void test3() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 2f , colorir.calculadora('%' , 4 , 2) , 0);	
	
	}
	
	@Test
	public void test2() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 4f , colorir.calculadora('^' , 2 , 2) , 0);		
	}
	
	@Test
	public void test1() {
	TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
	assertEquals( 3f , colorir.calculadora('+' , 1 , 2) , 0);
		
	}
	
	@Test
	public void test6() {
	TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
	assertEquals( 3f , colorir.calculadora('a' , 1 , 2) , 0);
		
	}

}