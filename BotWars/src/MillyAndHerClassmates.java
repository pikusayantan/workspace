import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MillyAndHerClassmates {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCases=Integer.parseInt(br.readLine());
		int output[][]=new int[testCases][2];
		for(int i=0;i<testCases;i++){
			boolean start=false;
			boolean done=false;
			int max=0;int min=0;
			int pos1=-1;int pos2=-1;
			int noOfStudents=Integer.parseInt(br.readLine());
			int seq[]=new int[noOfStudents];
			String eachnumber[] = br.readLine().split(" ");
			for(int c=0;c<noOfStudents;c++){
				seq[c]=Integer.parseInt(eachnumber[c]);
			}
			for(int j=0;j<noOfStudents-1;j++ ){
				if(seq[j]>seq[j+1] && !start){
					if(j==0){
						max=seq[j];
						pos1=1;
					}else{
						min=seq[j-1];
						max=seq[j];
						if(seq[j+1]<min){
							pos1=-1;pos2=-1;
							break;
						}
						pos1=j+1;
					}
					start=true;
				}else if(start && seq[j]>seq[j+1]){
					if(done){
						pos1=-1;pos2=-1;
						break;
					}
					pos2=j+1+1;
					continue;
				}else if(start && seq[j]<seq[j+1] && !done){
					if(seq[j+1]<max || seq[j]<min){
						pos1=-1;pos2=-1;
						break;
					}else{
						pos2=j+1;
						done=true;
					}
				}
			}
			output[i][0]=pos1;
			output[i][1]=pos2;
		}
		
		for(int i=0;i<testCases;i++){
			System.out.println(output[i][0]+" "+output[i][1]);
		}
	}
}