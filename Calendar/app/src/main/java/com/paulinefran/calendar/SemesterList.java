package com.paulinefran.calendar;

public class SemesterList {
	Semester head;
	Semester last;
	
	SemesterList(){
		head = null;
		last = head;
	}
	
	public void addSemester(Semester sem){
		// if list is empty
				if(head == null){
					head = sem;
					head.next = null;
				}
				
				// list not empty
				else{
					Semester current = head;
					Semester next = head.next;
					while (current != null){
						current = next;
						next = current.next;
					}
					current = sem;
				}
	}
	
	public Semester getSemList(){
		return head;
	}
}
