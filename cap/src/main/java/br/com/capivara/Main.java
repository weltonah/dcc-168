package br.com.capivara;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> aux = new ArrayList<Integer>();

		int b = 8, c = 8;
		;
		switch (b) {
		case 1:
			int a = 9;
			break;
		case 2:
			break;
		case 3:
			while (b < 7) {
				if (b == 5)
					break;
				b++;
			}
		case 4:
			for (int i = 0; i < 10; i++) {
				if (i == 12)
					break;
				c = c + i;
			}
		}
		System.out.println(b);
	}

	
	
}
