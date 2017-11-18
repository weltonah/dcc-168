
public class testeSwitch {
	public int testSwitch() {
		int b = 8;
		int c = 90;
		int d = 77;
		switch (b) {
		case 1:
			c = c + c;
			break;
		case 2:
			break;
		case 3:
			for (int i = 0; i < 10; i++) {
				b = c;
			}
			break;
		case 4:
			if (b < c) {
				b++;
			} else {
				d = 56;
			}
			break;
		case 5:
			return 3;
		default:
			System.out.println(d);
		}
		
		
		
		
		return 5;
	}
}
