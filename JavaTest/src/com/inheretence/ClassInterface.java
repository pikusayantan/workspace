package com.inheretence;

interface AI{

	void print();
	void print3();
}

class BI implements AI{
	BI(){
		System.out.println("inside B()");
	}	
	public void print(){
		System.out.println("inside B print()");
	}
	void print2(){		
		System.out.println("inside B print2()");
	}	
	public void print3(){		
		System.out.println("inside B print3()");
	}		
}

public class ClassInterface {

	public static void main(String[] args) {
		AI a=new BI();
		

	}

}
