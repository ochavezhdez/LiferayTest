/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.student.service.impl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.student.dto.StudentDTO;
import com.liferay.student.exception.StudentCityException;
import com.liferay.student.exception.StudentContactNoException;
import com.liferay.student.exception.StudentEnrollmentNoException;
import com.liferay.student.exception.StudentFirstNameException;
import com.liferay.student.exception.StudentLastNameException;
import com.liferay.student.model.Student;
import com.liferay.student.service.base.StudentLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.student.model.Student",
	service = AopService.class
)
public class StudentLocalServiceImpl extends StudentLocalServiceBaseImpl {

	public Student deleteStudent(Student student) {
		studentPersistence.remove(student);

		return student;
	}

	public Student deleteStudent(long studentId) throws PortalException {
		Student student = studentPersistence.findByPrimaryKey(studentId);

		return deleteStudent(student);
	}

	public List<Student> getStudents(long groupId) {
		return studentPersistence.findByGroupId(groupId);
	}

	public List<Student> getStudents(long groupId, int start, int end, OrderByComparator<Student> obc) {
		return studentPersistence.findByGroupId(groupId, start, end, obc);
	}

	public List<Student> getStudents(long groupId, int start, int end) {
		return studentPersistence.findByGroupId(groupId, start, end);
	}

	public int getStudentsCount(long groupId) {
		return studentPersistence.countByGroupId(groupId);
	}

	public Student saveStudent(long userId, StudentDTO studentDTO, ServiceContext serviceContext)
			throws PortalException {
		User user = userLocalService.getUserById(userId);
		Date date = new Date();

		Student student;
		if (studentDTO.getStudentId() == GetterUtil.DEFAULT_LONG) {
			student = studentPersistence.create(counterLocalService.increment());
			student.setCreateDate(serviceContext.getCreateDate(date));
		} else {
			student = studentLocalService.getStudent(studentDTO.getStudentId());
		}

		student.setUuid(serviceContext.getUuid());
		student.setUserId(userId);
		student.setGroupId(serviceContext.getScopeGroupId());
		student.setCompanyId(user.getCompanyId());
		student.setUserName(user.getFullName());
		student.setModifiedDate(serviceContext.getCreateDate(date));
		student.setExpandoBridgeAttributes(serviceContext);

		student.setEnrollmentNo(studentDTO.getEnrollmentNo());
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setContactNo(studentDTO.getContactNo());
		student.setCity(studentDTO.getCity());

		studentPersistence.update(student);

		return student;
	}

	protected void validate(StudentDTO studentDTO) throws PortalException {
		if (Validator.isNull(studentDTO.getEnrollmentNo())) {
			throw new StudentEnrollmentNoException();
		}

		if (Validator.isNull(studentDTO.getFirstName())) {
			throw new StudentFirstNameException();
		}

		if (Validator.isNull(studentDTO.getLastName())) {
			throw new StudentLastNameException();
		}

		if (Validator.isNull(studentDTO.getContactNo())) {
			throw new StudentContactNoException();
		}

		if (Validator.isNull(studentDTO.getCity())) {
			throw new StudentCityException();
		}
	}

}
