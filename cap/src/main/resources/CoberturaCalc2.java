import static org.junit.Assert.*;

import org.junit.Test;

public class CoberturaCalc2 {

	
	@Test
	public void test2() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 4f , colorir.calculadora('^' , 2 , 2) , 0);		
	}
	

	}
	
	
