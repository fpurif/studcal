package com.paulinefran.calendar;

public class CourseComponent {
	public String title;
	public int dueDate;
	public int reminderDate;
	public int weight;
	public int actualScore;
	public int outOfScore;
	
	public CourseComponent(String name, int dDate, int reminder, int weight, int actual, int outOf){
		title = name;
		dueDate = dDate;
		reminderDate = reminder;
		this.weight = weight;
		actualScore = actual;
		outOfScore = outOf;
	}
	
}
