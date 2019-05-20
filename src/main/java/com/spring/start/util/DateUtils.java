package com.spring.start.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Locale;



public class DateUtils {

	public static ArrayList<String> parse(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷 
		
		ArrayList<String> datelist = new ArrayList<>();
				
		for(String tmp : str.split(",")) {
			Calendar c1 = new GregorianCalendar();
			
			if(tmp.equals("오늘")||tmp.equals("금일")) {
				datelist.add(sdf.format(c1.getTime()));
			}else if(tmp.equals("어제")) {
				c1.add(Calendar.DATE, -1); 
				datelist.add(sdf.format (c1.getTime()));
			}else if(tmp.equals("그제")) {
				c1.add(Calendar.DATE, -2); 
				datelist.add(sdf.format (c1.getTime()));
			}else if(!dateCheck(tmp,"yyyyMMdd").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
				datelist.add(sdf.format(transFormat.parse(dateCheck(tmp,"yyyyMMdd"))));
			}else if(!dateCheck(tmp,"yyyy.MM.dd").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");
				datelist.add(sdf.format(transFormat.parse(dateCheck(tmp,"yyyy.MM.dd"))));
			}else if(!dateCheck(tmp,"yyyy/MM/dd").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
				datelist.add(sdf.format(transFormat.parse(dateCheck(tmp,"yyyy/MM/dd"))));
			}else if(!dateCheck(tmp,"yyyy-MM-dd").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				datelist.add(sdf.format(transFormat.parse(dateCheck(tmp,"yyyy-MM-dd"))));
			}else if(!dateCheck(tmp,"yyyy년MM월dd일").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년MM월dd일");
				datelist.add(sdf.format(transFormat.parse(dateCheck(tmp,"yyyy년MM월dd일"))));
			}else if(!dateCheck(tmp,"MM월dd일").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년MM월dd일");
				datelist.add(sdf.format(transFormat.parse(dateCheck("2018"+"년"+tmp,"yyyy년MM월dd일"))));
			}else if(!dateCheck(tmp,"yyyy년MM월").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년MM월dd일");
				datelist.add(sdf.format(transFormat.parse(dateCheck(tmp+"1일","yyyy년MM월dd일"))));
				int month = transFormat.parse(dateCheck(tmp+"1일","yyyy년MM월dd일")).getMonth()+1;

				if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12 ) {
					datelist.add(sdf.format(transFormat.parse(dateCheck(tmp+"31일","yyyy년MM월dd일"))));				
				}else if(month==4 || month==6 || month==9 || month==11 ) {
					datelist.add(sdf.format(transFormat.parse(dateCheck(tmp+"30일","yyyy년MM월dd일"))));	
				}else if(month==2) {
					datelist.add(sdf.format(transFormat.parse(dateCheck(tmp+"28일","yyyy년MM월dd일"))));	
				}
			}else if(!dateCheck(tmp,"MM월").equals("")) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년MM월dd일");
				datelist.add(sdf.format(transFormat.parse(dateCheck("2018"+"년"+tmp+"1일","yyyy년MM월dd일"))));
				int month = transFormat.parse(dateCheck("2018"+"년"+tmp+"1일","yyyy년MM월dd일")).getMonth()+1;

				if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12 ) {
					datelist.add(sdf.format(transFormat.parse(dateCheck("2018"+"년"+tmp+"31일","yyyy년MM월dd일"))));				
				}else if(month==4 || month==6 || month==9 || month==11 ) {
					datelist.add(sdf.format(transFormat.parse(dateCheck("2018"+"년"+tmp+"30일","yyyy년MM월dd일"))));	
				}else if(month==2) {
					datelist.add(sdf.format(transFormat.parse(dateCheck("2018"+"년"+tmp+"28일","yyyy년MM월dd일"))));	
				}
			} else if ("태양절".equals(tmp)) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
				datelist.add(sdf.format(transFormat.parse("20180415")));
			} else if ("김정일 생일".equals(tmp) || "김정일생일".equals(tmp)) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
				datelist.add(sdf.format(transFormat.parse("20180216")));
			} else if ("정전 협정 체결일".equals(tmp)) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
				datelist.add(sdf.format(transFormat.parse("20180727")));
			} else if ("국제 아동절".equals(tmp) || "국제아동절".equals(tmp)) {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
				datelist.add(sdf.format(transFormat.parse("20180601")));
			}
			
		}
		
		Collections.sort(datelist);
		//System.out.println(datelist.toString());
		//"startdate="+datelist.get(0)+","+"enddate="+datelist.get(datelist.size()-1)
		return datelist;
	}
	
	public static String dateCheck(String date, String format) {
        SimpleDateFormat dateFormatParser = new SimpleDateFormat(format, Locale.KOREA);
        dateFormatParser.setLenient(false);
        try {
            dateFormatParser.parse(date);
            return dateFormatParser.format(dateFormatParser.parse(date)).toString();
        } catch (Exception Ex) {
            return "";
        }
    }

	// 날짜 테스트용
	public static void main(String args[]) {
		try {
			System.out.println(parse("2019-6-21,05월18일,2019년05월,20190524,2019-5-25,오늘,어제,12월"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

