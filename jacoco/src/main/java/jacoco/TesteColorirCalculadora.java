package jacoco;

public class TesteColorirCalculadora {
	
	public float calculadora(char opcao , int num1 , int num2) {
		
		switch (opcao) {
		case '+':
			
			return num1 + num2 ;
			
			
		case '%' :			
			float aux = num1 ;
			int i = 0 ;
			while(aux >= num2) {
				i++;
				aux = aux - num2;
			}
			
			return i;
			
		case '/':
			
			if(num2 == 0) {
				return 0;
			}
			else
				return num1/num2;			
			
			
		case '^':
			float aux2 = num1;
			for(int j = 1 ; j < num2 ; j++) {
				aux2 = aux2 * num1;
				
			}
			return aux2;
			
		
			default:
				return 0;
		}
		
		
		
	}
}
