package com.paulinefran.calendar;

public class Course {
	
	public String name;
	public String colourHex;
	public Course next;

	Course(String title, String colour){
		name = title;
		colourHex = colour;
	}
}
