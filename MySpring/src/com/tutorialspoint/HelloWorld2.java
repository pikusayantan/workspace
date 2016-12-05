package com.tutorialspoint;

public class HelloWorld2 {
	private String message;

	public void setMessage(String messages) {
		System.out.println("in setter method2");
		this.message = messages;
		System.out.println("setter message2 : " + this.message);
	}

	public void getMessage() {
		System.out.println("Your Message 20 : " + message);
	}
}