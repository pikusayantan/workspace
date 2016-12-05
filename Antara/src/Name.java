import java.util.Scanner;

public class Name {
	public static void main(String args[]) {
		Scanner a = new Scanner(System.in);
		System.out.print("tomar ki biye hoyeche?");
		String y = a.next();
		if (y.equals("yes")) {
			System.out.println("tahole tomar naam Antara Mandal");
		} else {
			System.out.println("tomar naam Antara Sinha");
		}
	}
}
