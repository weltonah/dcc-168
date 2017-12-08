package jacoco;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoberturaCalc1 {

	@Test
	public void test1() {
	TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
	assertEquals( 3f , colorir.calculadora('+' , 1 , 2) , 0);
		
	}	
	
	
	

}
