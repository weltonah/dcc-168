
public class testeReturn {

	public int exemploRetunr(int y) {
		int a = 90;
		int b = 3; 
		int c = -9;
		if (a<b){
			return b;
		}else {
			b = c;
		}
		
		if (a==c){
			a++;
		}else {
			b = c - a;
			return c;
		}	
		
		if(c >900) {
			return c+4;
		}
		
		for(int i =0; i < 60; i++) {
			if(i ==8) {
				return i;
			}
		}
		
		while(b>=c) {
			while(b==y) {
				if(c == 6) {
					return b+c;
				}
			}
			a = a *b;
		}
		
		return y;
	}

}
