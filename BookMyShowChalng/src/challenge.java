import java.util.Scanner;

public class challenge {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder output=new StringBuilder();
		int testCase = in.nextInt();
		for (int i = 0; i < testCase; i++) {
			
			int item = Integer.parseInt(in.next());
			int shop = Integer.parseInt(in.next());
			for (int j = 0; j < item; j++) {
				double lowest = 200.0;
				int index = 0;
				for (int k = 0; k < shop; k++) {
					int discount1 = Integer.parseInt(in.next());
					int discount2 = Integer.parseInt(in.next());
					int discount3 = Integer.parseInt(in.next());
					double finalPrice = ((100 - (100 * discount1 / 100)) - (100 - (100 * discount1 / 100))
							* discount2 / 100)
							- ((100 - (100 * discount1 / 100)) - (100 - (100 * discount1 / 100))
									* discount2 / 100) * discount3 / 100;
					if (finalPrice < lowest) {
						lowest = finalPrice;
						index = k + 1;
					}
				}
				output.append(index);
				output.append(" ");
			}
			output.append("\n");
		}
		in.close();
		System.out.println(output.toString());
	}
}
