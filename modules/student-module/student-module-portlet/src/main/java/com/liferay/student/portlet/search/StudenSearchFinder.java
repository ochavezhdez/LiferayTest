package com.liferay.student.portlet.search;

import java.util.List;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.student.model.Student;
import com.liferay.student.service.StudentLocalService;

public class StudenSearchFinder implements StudentSeacrh {

	private StudentLocalService studentLocalService;
	private String pattern;

	public StudenSearchFinder(StudentLocalService studentLocalService, String pattern) {
		this.studentLocalService = studentLocalService;
		this.pattern = pattern;
	}

	@Override
	public int getStudentsCount() throws Exception {
		return studentLocalService.getStudentsCount();
	}

	@Override
	public List<Student> getStudents(long groupId, int start, int end) throws Exception {
		if (Validator.isNull(pattern) || pattern.isEmpty()) {
			return studentLocalService.getStudents(groupId, start, end);
		}

		return studentLocalService.getStudents(groupId, pattern, start, end);
	}

}
