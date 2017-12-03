package jacoco;

import org.junit.Test;

import junit.framework.TestCase;

public class maintest extends TestCase {
	@Test
	public void teste1() {
		mult m = new mult();
		assertEquals(12, m.multiplicacao(4, 3));
	}
}
