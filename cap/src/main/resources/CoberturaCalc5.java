
import static org.junit.Assert.*;

import org.junit.Test;

public class CoberturaCalc5 {


	@Test
	public void test5() {
		TesteColorirCalculadora colorir = new TesteColorirCalculadora();	
		assertEquals( 3f , colorir.calculadora('/' , 6 , 2) , 0);
	}
	
	

}