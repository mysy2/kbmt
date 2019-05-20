package com.spring.start.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			"TITLE,CONTENT,NERCONTENT",
			"TITLE,CONTENT,USER,DATE,AREA,COMMENT,URL"
		}
	};
	private static final int PAGE_START = 0;
	private static final int RESULT_COUNT = 10;
	private static final String SORT = "DATE/DESC";
	
	public List<String> srch(Map<String, String> mQuery) {
		List<String> result = new ArrayList<>();
		String prefix = "";
		
		String query = mQuery.get("query");
		String dfield = mQuery.get("dfield");
		String sdate = mQuery.get("sdate").replace("-", "/");
		String edate = mQuery.get("edate").replace("-", "/");
		
		if (null == sdate || "".equals(sdate)) {
			sdate = "1970/01/01";
		}
		if (null == edate || "".equals(edate)) {
			edate = "2030/12/31";
		}
		
		// collection
		String collection = COLLECTION_INFO[0][0];
		String SEARCH_FIELD = COLLECTION_INFO[0][1];
		String DOCUMENT_FIELD = dfield;

		// object
		Search search = new Search();

		// setting common
		search.w3SetCodePage("UTF-8");
		search.w3SetQueryLog(1);
		search.w3SetCommonQuery(query, 0);
		search.w3SetSpellCorrectionQuery(query, 0);

		// setting collection
		search.w3AddCollection(collection);
		search.w3SetQueryAnalyzer(collection, 0, 1, 1, 1);
		search.w3SetPageInfo(collection, PAGE_START, RESULT_COUNT);
		search.w3SetSortField(collection, SORT);
		search.w3SetDateRange(collection, sdate, edate);
		search.w3SetSearchField(collection, SEARCH_FIELD);
		search.w3SetSearchFieldScore(collection, SEARCH_FIELD);
		search.w3SetDocumentField(collection, DOCUMENT_FIELD);
		search.w3SetPrefixQuery(collection, prefix, 1);
		search.w3SetHighlight(collection, 1, 1);
		search.w3ConnectServer(SERVER_IP, SERVER_PORT, SERVER_TIMEOUT);
		search.w3ReceiveSearchQueryResult(3);

		int count = search.w3GetResultCount(collection);
		int totalCount = search.w3GetResultTotalCount(collection);
		
		System.out.println(totalCount);

		if (totalCount > 0) {
			for (int i = 0; i < count; i++) {
				if(dfield.equals("DATE")) {
					if ("".equals(search.w3GetField(collection, dfield, i))) {
						continue;
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
					SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					
					try {
						result.add(sdf.format(transFormat.parse(search.w3GetField(collection, dfield, i))));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else if (dfield.equals("TITLE,USER")) {
					result.add("기사 : " + search.w3GetField(collection, "TITLE", i) + " / 인물 : " + search.w3GetField(collection, "USER", i));
				} else {
					if ("".equals(search.w3GetField(collection, dfield, i))) {
						continue;
					}
					
					result.add(search.w3GetField(collection, dfield, i));
				}
			}
		}

		if (search != null) {
			search.w3CloseServer();
		}

		return result;
	}
}
