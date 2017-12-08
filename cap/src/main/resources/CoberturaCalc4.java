import static org.junit.Assert.*;

import org.junit.Test;

public class CoberturaCalc4 {


	@Test
	public void test4() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 0f , colorir.calculadora('/' , 2 , 0) , 0);	
	}
	

	
	

}