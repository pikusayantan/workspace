package com.tutorialspoint;

public class HelloWorld {
	private String message;

	public void setMessage(String messages) {
		System.out.println("in setter method");
		this.message = messages;
		System.out.println("setter message: " + this.message);
	}

	public void getMessages() {
		System.out.println("Your Message : " + message);
	}
	
	public void init(){
		System.out.println("Init is called hello");
	}
	public void destroyer(){
		System.out.println("destroy is been called");
	}
}