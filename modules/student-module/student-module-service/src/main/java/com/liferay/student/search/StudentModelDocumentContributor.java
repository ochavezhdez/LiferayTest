package com.liferay.student.search;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.student.model.Student;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.student.model.Student",
	service = ModelDocumentContributor.class
)
public class StudentModelDocumentContributor implements ModelDocumentContributor<Student> {

	private static final Log log = LogFactoryUtil.getLog(StudentModelDocumentContributor.class);

	@Override
	public void contribute(Document document, Student student) {
		try {
			Locale defaultLocale = PortalUtil.getSiteDefaultLocale(student.getGroupId());
			document.addDate(Field.MODIFIED_DATE, student.getModifiedDate());
			document.addText("city", student.getCity());

			String localizedTitle = LocalizationUtil.getLocalizedName(Field.TITLE, defaultLocale.toString());
			String localizedContent = LocalizationUtil.getLocalizedName(Field.CONTENT, defaultLocale.toString());

			document.addText(localizedTitle, student.getFirstName());
			document.addText(localizedContent, student.getLastName());
		} catch (PortalException pe) {
			if (log.isWarnEnabled()) {
				log.warn("Unable to index entry " + student.getStudentId(), pe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
