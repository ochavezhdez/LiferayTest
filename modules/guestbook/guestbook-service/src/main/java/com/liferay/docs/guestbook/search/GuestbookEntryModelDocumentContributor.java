package com.liferay.docs.guestbook.search;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry",
	service = ModelDocumentContributor.class
)
public class GuestbookEntryModelDocumentContributor implements ModelDocumentContributor<GuestbookEntry> {

	private static final Log log = LogFactoryUtil.getLog(GuestbookEntryModelDocumentContributor.class);

	@Reference
	private GuestbookLocalService guestbookLocalService;

	@Override
	public void contribute(Document document, GuestbookEntry entry) {
		try {
			Locale defaultLocale = PortalUtil.getSiteDefaultLocale(entry.getGroupId());

			document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
			document.addText("entryEmail", entry.getEmail());

			String localizedTitle = LocalizationUtil.getLocalizedName(Field.TITLE, defaultLocale.toString());
			String localizedContent = LocalizationUtil.getLocalizedName(Field.CONTENT, defaultLocale.toString());

			document.addText(localizedTitle, entry.getName());
			document.addText(localizedContent, entry.getMessage());

			long guestbookId = entry.getGuestbookId();

			Guestbook guestbook = guestbookLocalService.getGuestbook(guestbookId);

			String guestbookName = guestbook.getName();

			String localizedGbName = LocalizationUtil.getLocalizedName(Field.NAME, defaultLocale.toString());

			document.addText(localizedGbName, guestbookName);
		} catch (PortalException pe) {
			if (log.isWarnEnabled()) {
				log.warn("Unable to index entry " + entry.getEntryId(), pe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
