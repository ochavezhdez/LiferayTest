package com.liferay.student.cmd.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.student.constants.StudentPortletKeys;
import com.liferay.student.model.Student;
import com.liferay.student.service.StudentLocalService;

@Component(
		property = {
				"javax.portlet.name=" + StudentPortletKeys.STUDENT,
				"mvc.command.name=saveStudentAction"
		},
		service = MVCActionCommand.class
)
public class SaveStudentActionCommand extends BaseMVCActionCommand {

	@Reference
	private CounterLocalService counterLocalService;
	@Reference
	private StudentLocalService studentLocalService;

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		long studentId = ParamUtil.getLong(actionRequest, "studentId", GetterUtil.DEFAULT_LONG);
		String enrollmentNo = ParamUtil.getString(actionRequest, "enrollmentNo", GetterUtil.DEFAULT_STRING);
		String firstName = ParamUtil.getString(actionRequest, "firstName", GetterUtil.DEFAULT_STRING);
		String lastName = ParamUtil.getString(actionRequest, "lastName", GetterUtil.DEFAULT_STRING);
		String contactNo = ParamUtil.getString(actionRequest, "contactNo", GetterUtil.DEFAULT_STRING);
		String city = ParamUtil.getString(actionRequest, "city", GetterUtil.DEFAULT_STRING);

		Student student = null;
		boolean isAdd = false;
		if (studentId == GetterUtil.DEFAULT_LONG) {
			isAdd = true;
			studentId = counterLocalService.increment(Student.class.getName());
			student = studentLocalService.createStudent(studentId);
		} else {
			student = studentLocalService.getStudent(studentId);
		}
		student.setEnrollmentNo(enrollmentNo);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setContactNo(contactNo);
		student.setCity(city);

		if (isAdd) {
			studentLocalService.addStudent(student);
		} else {
			studentLocalService.updateStudent(student);
		}
	}

}
