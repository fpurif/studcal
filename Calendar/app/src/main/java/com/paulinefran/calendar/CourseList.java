package com.paulinefran.calendar;

public class CourseList {
	
	Course head;
	Course last;
	
	CourseList(){
		head = null;
		last = head;
	}
	
	public void addCourse(Course coursename){
		
		// if list is empty
		if(head == null){
			head = coursename;
			head.next = null;
		}
		
		// list not empty
		else{
			Course current = head;
			Course next = head.next;
			while (current != null){
				current = next;
				next = current.next;
			}
			current = coursename;
		}
	}
}
