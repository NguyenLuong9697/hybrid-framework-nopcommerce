package javabasic;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


public class test {

	
	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//		System.out.println(formater.format(calendar.getTime()));
		java.util.Date date=new java.util.Date();  
		//System.out.println(date);  
		// System.out.printf("%1$s  %2tA %2$tB %2$td, %2$tY", 
              //   "Date la:", date);
		 System.out.println(LocalDateTime.now().getYear());       // 2015
		    System.out.println(LocalDateTime.now().getMonth());      // SEPTEMBER
		    System.out.println(LocalDateTime.now().getDayOfMonth()); // 29
		    System.out.println(LocalDateTime.now().getHour());       // 7
		    System.out.println(LocalDateTime.now().getMinute());     // 36
		    System.out.println(LocalDateTime.now().getDayOfWeek().toString().toLowerCase());
		    // 51
		    String script1="return $(document.evaluate(\"//input[@id='Address_FirstName']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
		  //  System.out.println(LocalDateTime.now().get(ChronoField.MILLI_OF_SECOND)); // 100
		    String s="//input[@id='Address_FirstName']";
		    String script= "return $(document.evaluate(" +"\""+  s +"\""+", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
//		    
//		    String s1 = "abc";
//	        String s2 = "truong";
//	        String s = "truong" + " \"" + s1+ "\"" + s2;
	         System.out.println(script1);
		   System.out.println(script);
	}
	
}
