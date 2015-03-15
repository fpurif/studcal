package com.paulinefran.calendar;

public class Semester {
	
	public String title;
	public CourseList courses;
	public Semester next;
	
	Semester(String name){
		title = name;
		courses = new CourseList();
	}
	
	public void addCourse(Course coursename){
		
			courses.addCourse(coursename);
		}
	}

