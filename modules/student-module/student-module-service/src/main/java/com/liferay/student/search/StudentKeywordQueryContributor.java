package com.liferay.student.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.student.model.Student",
	service = KeywordQueryContributor.class
)
public class StudentKeywordQueryContributor implements KeywordQueryContributor {

	@Reference
	protected QueryHelper queryHelper;

	@Override
	public void contribute(String keywords, BooleanQuery booleanQuery,
			KeywordQueryContributorHelper keywordQueryContributorHelper) {
		SearchContext searchContext = keywordQueryContributorHelper.getSearchContext();
		queryHelper.addSearchLocalizedTerm(booleanQuery, searchContext, Field.TITLE, false);
		queryHelper.addSearchLocalizedTerm(booleanQuery, searchContext, Field.CONTENT, false);
		queryHelper.addSearchLocalizedTerm(booleanQuery, searchContext, "city", false);
	}

}
