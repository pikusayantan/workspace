import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TheGreatKian {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		String eachnumber[] = br.readLine().split(" ");
		long a=0;
		long b=0;
		long c=0;
		
		for (int i = 0; i < N; i++) {
			int s=i%3;
			if(s==0){
				a=a+Long.parseLong(eachnumber[i]);
			}else if(s==1){
				b=b+Long.parseLong(eachnumber[i]);
			}else{
				c=c+Long.parseLong(eachnumber[i]);
			}
		}
		System.out.println(a+" "+b+" "+c);
	}
}