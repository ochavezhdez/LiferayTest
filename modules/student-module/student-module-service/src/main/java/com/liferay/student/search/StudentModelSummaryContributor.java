package com.liferay.student.search;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.student.model.Student",
	service = ModelSummaryContributor.class
)
public class StudentModelSummaryContributor implements ModelSummaryContributor {

	@Override
	public Summary getSummary(Document document, Locale locale, String snippet) {
		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	private Summary createSummary(Document document) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + "firstName", "firstName");

		return new Summary(title, StringPool.BLANK);
	}

}
