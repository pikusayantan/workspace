package admin;

import java.util.Random;

public class teststs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=1;i<100;i++){
//			Thread.sleep(1000);
			Random rand = new Random();
			int selected = rand.nextInt(100);
			System.out.println(i);
		}
	}

}
