package com.liferay.docs.guestbook.search;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.docs.guestbook.service.GuestbookEntryLocalService;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry",
	service = ModelIndexerWriterContributor.class
)
public class GuestbookEntryModelIndexerWriterContributor implements ModelIndexerWriterContributor<GuestbookEntry> {

	@Reference
	protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;

	@Reference
	protected GuestbookEntryLocalService guestbookEntryLocalService;

	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable,
			ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

		batchIndexingActionable.setPerformActionMethod((GuestbookEntry entry) -> {
			Document document = modelIndexerWriterDocumentHelper.getDocument(entry);

			batchIndexingActionable.addDocuments(document);

		});
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dynamicQueryBatchIndexingActionableFactory
				.getBatchIndexingActionable(guestbookEntryLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(GuestbookEntry entry) {
		return entry.getCompanyId();
	}

}
