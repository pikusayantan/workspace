package com.inheretence;

class A{
	A(int a){
		System.out.println("inside A(a) ");
	}
	
	A(){
		System.out.println("inside A() ");
	}
	/*A(int s){
		System.out.println("inside A(int): "+s);
	}*/
	void print(){
		System.out.println("inside A print()");
	}
	void print3(){
		System.out.println("inside A print3()");
	}
}

class B extends A{
	B(){
//		 super(10);
		 System.out.println("inside B()");		
	}
	
	B(int i){
		System.out.println("inside B(int):"+i);
	}
	
	void print(){
		System.out.println("inside B print()");
	}
	
	void print2(){		
		System.out.println("inside B print2()");
	}
	
}


public class ClassExtend {

	public static void main(String[] args) {
		A a= new A();
		
//		B b=new B();
		
		//a.print();
		
	}

}
