import static org.junit.Assert.*;

import org.junit.Test;

public class CoberturaCalc3 {

	
	@Test
	public void test3() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 2f , colorir.calculadora('%' , 4 , 2) , 0);	
	
	}
	

}