package com.liferay.student.portlet.search;

import java.util.List;

import com.liferay.student.model.Student;

public interface StudentSeacrh {
	
	int getStudentsCount() throws Exception;
	
	List<Student> getStudents(long groupId, int start, int end) throws Exception;

}
