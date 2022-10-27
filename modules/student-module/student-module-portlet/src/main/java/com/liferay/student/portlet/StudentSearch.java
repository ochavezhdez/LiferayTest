package com.liferay.student.portlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.student.model.Student;
import com.liferay.student.service.StudentLocalService;

public class StudentSearch {

	private SearchContext searchContext;
	private StudentLocalService studentLocalService;

	public StudentSearch(HttpServletRequest httpServletRequest, String pattern,
			StudentLocalService studentLocalService) {
		searchContext = SearchContextFactory.getInstance(httpServletRequest);
		searchContext.setKeywords(pattern);
		searchContext.setAttribute("paginationType", "more");
		this.studentLocalService = studentLocalService;
	}

	public int getStudentsCount() throws SearchException {
		searchContext.setStart(0);
		searchContext.setEnd(studentLocalService.getStudentsCount());

		Indexer<Student> indexer = IndexerRegistryUtil.getIndexer(Student.class);
		Hits hits = indexer.search(searchContext);

		return hits.getDocs().length;
	}

	public List<Student> getStudents(int start, int end) throws PortalException {
		searchContext.setStart(start);
		searchContext.setEnd(end);

		Indexer<Student> indexer = IndexerRegistryUtil.getIndexer(Student.class);
		Hits hits = indexer.search(searchContext);
		List<Student> students = new ArrayList<>(hits.getDocs().length);
		for (int i = 0; i < hits.getDocs().length; i++) {
			Document doc = hits.doc(i);
			long entryId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
			Student student = studentLocalService.getStudent(entryId);
			students.add(student);
		}

		return students;
	}

}
