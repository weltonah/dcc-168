
public class testeIf {
	
	public static void main(String[] args) {
		int a = 9;
		if (a > 9) {
			a = 100;
		} else {
			a = -5;
		}
		int b = 10;
		if (a > 100 && b == 10 || a > b) {
		    b = 25;
		    if (b == 11) {
		        a = b;
		    }
		}
		int c = 9;
		int d = 89;
		if (c < d) {
		    c = 20;
		}
		if (c < d - 1) {
		    c=c-1;
		}
		
		
		if(a==0) {
			if(b>32) {
				if(c<7) {
					a = 60 - b;
				}
			}
		}
		if(a==0) {
			if(b>32) {
				if(c<7) {
					a = 60 - b;
				}else {
					a = a +a;
				}
			}
		}
		System.out.println(a);
	}
}
