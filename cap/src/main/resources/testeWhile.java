
public class testeWhile {
	public static void main(String[] args) {
		int a=0;
		int i = 0;
		while(i < 10) {
			a=a+i;
			i++;
		}
		int b=9;
		int j = 0;
		while(j <10) {
			a=a+i;
			int k =0;
			while(k<15) {
				k++;
			}
			j++;
		}
		
		while(b <10) {
			b=b+i;
			int k =0;
			while(b<15) {
			}
		}
		
		System.out.println(a);
	}
}
