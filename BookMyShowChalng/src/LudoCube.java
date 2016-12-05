import java.util.Random;
import java.util.Scanner;

public class LudoCube {

	public static void main(String[] antara) {
		Random rand = new Random();
		Scanner in = new Scanner(System.in);

		int totalBall = 300;

		int scoreKoi[] = new int[2];
		int scoreBuro[] = new int[2];

		String turn = "N";

		System.out.println("Tossing.........>>>");
		in.nextLine();
		int toss = rand.nextInt(2);
		System.out.println("tossss++++" + toss);
		if (toss == 0) {
			turn = "K";
			System.out.println("Koi's 1st batting!!!!!");
		} else {
			turn = "B";
			System.out.println("Buro 1st batting!!!!!");
		}

		int count = 0;
		while (count < totalBall) {

			System.out.println("count+++" + count);

			if (turn.equals("K")) {
				System.out.println("Koi Press K to bat");
				String player = in.nextLine();
				if (player.equalsIgnoreCase("K")) {
					count++;
					int hit = rand.nextInt(7);
					System.out.println("Koi Hits....." + hit);
					if (hit == 0) {

						System.out.println("Koi you got OUT!!!");
						scoreKoi[1] = scoreKoi[1] + 1;
						System.out.println("Score  koi:" + scoreKoi[0] + "/"
								+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
								+ scoreBuro[1]);
						if (scoreKoi[1] == 10 && toss == 0) {
							count = totalBall / 2;
							System.out.println("Score  koi:" + scoreKoi[0]
									+ "/" + scoreKoi[1] + "  Buro:"
									+ scoreBuro[0] + "/" + scoreBuro[1]);
							System.out
									.println("Koi All out,,now Buro will bat...");
						}
						if (scoreKoi[1] == 10 && toss == 1) {
							System.out.println("Score  koi:" + scoreKoi[0]
									+ "/" + scoreKoi[1] + "  Buro:"
									+ scoreBuro[0] + "/" + scoreBuro[1]);
							System.out
									.println("Koi All out,,now Buro wins the Match...");
							break;
						}
					} else {

						scoreKoi[0] = scoreKoi[0] + hit;
						System.out.println("Score  koi:" + scoreKoi[0] + "/"
								+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
								+ scoreBuro[1]);
					}

				} else {
					System.out.println("It is Koi's turn.....");
				}
			}

			if (turn.equals("B")) {
				System.out.println("Buro Press B to bat");
				String player = in.nextLine();
				if (player.equalsIgnoreCase("B")) {
					count++;
					int hit = rand.nextInt(7);
					System.out.println("buro hits....." + hit);
					if (hit == 0) {
						System.out.println("buro you got OUT!!!");
						scoreBuro[1] = scoreBuro[1] + 1;
						System.out.println("Score  koi:" + scoreKoi[0] + "/"
								+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
								+ scoreBuro[1]);
						if (scoreBuro[1] == 10 && toss == 1) {
							count = totalBall / 2;
							System.out
									.println("Buro All out,,now Koi will bat...");
							System.out.println("Score  koi:" + scoreKoi[0]
									+ "/" + scoreKoi[1] + "  Buro:"
									+ scoreBuro[0] + "/" + scoreBuro[1]);
						}
						if (scoreBuro[1] == 10 && toss == 0) {
							System.out.println("Score  koi:" + scoreKoi[0]
									+ "/" + scoreKoi[1] + "  Buro:"
									+ scoreBuro[0] + "/" + scoreBuro[1]);
							System.out
									.println("Buro All out,,now Koi wins the Match...");
							break;
						}
					} else {
						scoreBuro[0] = scoreBuro[0] + hit;
						System.out.println("Score  koi:" + scoreKoi[0] + "/"
								+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
								+ scoreBuro[1]);
					}

				} else {
					System.out.println("It is Buro's turn.....");
				}
			}

			if (count == totalBall) {
				if (scoreBuro[0] < scoreKoi[0]) {
					System.out.println("Score  koi:" + scoreKoi[0] + "/"
							+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
							+ scoreBuro[1]);
					System.out.println("Koi win the match");
				} else {
					System.out.println("Score  koi:" + scoreKoi[0] + "/"
							+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
							+ scoreBuro[1]);
					System.out.println("Buro wins the match");
				}
			}
			if (toss == 0 && scoreBuro[0] > scoreKoi[0]) {
				System.out.println("Score  koi:" + scoreKoi[0] + "/"
						+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
						+ scoreBuro[1]);
				System.out.println("Buro wins the match");
				break;
			}
			if (toss == 1 && scoreBuro[0] < scoreKoi[0]) {
				System.out.println("Score  koi:" + scoreKoi[0] + "/"
						+ scoreKoi[1] + "  Buro:" + scoreBuro[0] + "/"
						+ scoreBuro[1]);
				System.out.println("koi wins the match");
				break;
			}

			if (count % (totalBall / 2) == 0) {
				if (toss == 0 && count > 1) {
					turn = "B";
				}
				if (toss == 1 && count > 1) {
					turn = "K";
				}
			}
			System.out.println("-------------------------------");
		}

		System.out.println("game over");
		in.close();
	}
}
