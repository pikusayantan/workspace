import java.util.Random;

public class StringClass {
	Random randomGenerator =new Random();
	int randomNumber=randomGenerator.nextInt(21);

    public static void main(String args[] ) throws Exception {
       
    	String st="hi";
        System.out.println(st.hashCode()+" "+st.getBytes());
        String st2=new String("hi");
        System.out.println(st2.hashCode()+" "+st2.getBytes());
//        st2=st;
        System.out.println(st2.hashCode()+" "+st2.getBytes());
        System.out.println(st==st2);
        
        
//        System.out.println(randomNumber);
    }
}