
public class testeForWhileBreakContinue {
	public static void main(String[] args) {
		int a=0;
		for (int i = 0; i < 10; i++) {
			if (i == 8) {
				continue;
			}
		}
		a = a + i;
		int b = 9;

		for (int j = 0; j < 10; j++) {
			if (b > 10) {
				break;
			} else {
				b = 90;
			}
		}
		int d = 0;
		while (b < 30) {
			if (d == 10) {
				continue;
			}
			d++;
		}
		int k = 0;
		while (k < 30) {
			if (k == 10) {
				break;
			}
			k++;
		}
		System.out.println(a);
	}

}
