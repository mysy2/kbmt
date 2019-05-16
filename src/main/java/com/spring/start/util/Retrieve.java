package com.spring.start.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import QueryAPI530.Search;

@Repository
public class Retrieve {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 7000;
	private static final int SERVER_TIMEOUT = 10000;
	private static final String[][] COLLECTION_INFO = new String[][] {
		{
			"kbmt",
			"NERCONTENT",
			"TITLE,CONTENT,USER,DATE,AREA,COMMENT,URL"
		}
	};
	private static final int PAGE_START = 0;
	private static final int RESULT_COUNT = 10;
	private static final String SORT = "RANK/DESC";
	private static final String START_DATE = "1970/01/01";
	private static final String END_DATE = "2030/12/31";
	
	public List<Map<String, String>> srch1(String query, String dfield) {
		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> doc = null;
		String prefix = "";
		
		// collection
		String collection = COLLECTION_INFO[0][0];
		String SEARCH_FIELD = COLLECTION_INFO[0][1];
		String DOCUMENT_FIELD = dfield;
		String[] dfields = DOCUMENT_FIELD.split(",");

		// object
		Search search = new Search();

		// setting common
		search.w3SetCodePage("UTF-8");
		search.w3SetQueryLog(1);
		search.w3SetCommonQuery(query, 0);
		search.w3SetSpellCorrectionQuery(query, 0);

		// setting collection
		search.w3AddCollection(collection);
		search.w3SetQueryAnalyzer(collection, 1, 1, 1, 1);
		search.w3SetPageInfo(collection, PAGE_START, RESULT_COUNT);
		search.w3SetSortField(collection, SORT);
		search.w3SetDateRange(collection, START_DATE, END_DATE);
		search.w3SetSearchField(collection, SEARCH_FIELD);
		search.w3SetSearchFieldScore(collection, SEARCH_FIELD);
		search.w3SetDocumentField(collection, DOCUMENT_FIELD);
		search.w3SetPrefixQuery(collection, prefix, 1);
		search.w3SetHighlight(collection, 1, 1);
		search.w3ConnectServer(SERVER_IP, SERVER_PORT, SERVER_TIMEOUT);
		search.w3ReceiveSearchQueryResult(3);

		int count = search.w3GetResultCount(collection);
		int totalCount = search.w3GetResultTotalCount(collection);

		if (totalCount > 0) {
			for (int i = 0; i < count; i++) {
				doc = new HashMap<>();
				
				doc.put("count", totalCount + "");

				for (String d : dfields) {
					doc.put(d, search.w3GetField(collection, d, i));
				}

				result.add(doc);
			}
		}

		if (search != null) {
			search.w3CloseServer();
		}

		return result;
	}
}
