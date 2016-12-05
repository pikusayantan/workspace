package com.sample;


class Shape{
	
	private int peri;
	private final static int pi=3;
	
	Shape(){
		System.out.println("here");
	}
	Shape(int a){
		System.out.println("here int");
	}
	
	void triangle(int a, int b, int c){
		peri=a+b+c;
	}

	
	void print(){
		System.out.println("perimeter is "+peri);
	}
	
}

public class Sample {
	
	public static void main(String adh[]){
		Shape trngle= new Shape(85);
		/*
		trngle.triangle(1,2,3);
		trngle.print();
		
		trngle.triangle(1,2,10);
		trngle.print();
		
		
		Shape trngle1= new Shape();
		trngle1.triangle(5,2,8);
		trngle1.print();
		
		trngle.print();*/
	}		
}
