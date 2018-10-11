//package org.apache.commons.count.distribution;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.jsoup.Jsoup;

import com.opencsv.CSVWriter;

public class WordCount{
	public static void main(String[] args) throws IOException {
		//using hash map for storing words
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		File dir = new File("/Users/wilsenkosasih/desktop/repository/");		
		
		for (int i=0; i< dir.list().length; i++) {

			//read html into string
			StringBuilder bldr = new StringBuilder();
			String str;

			BufferedReader in = new BufferedReader(new FileReader("/Users/wilsenkosasih/desktop/repository/html_"+(i+1)+".html"));
			while((str = in.readLine())!=null)
				bldr.append(str);
			in.close();

			String html = bldr.toString();
    	    	
			String notag = Jsoup.parse(html).text();
			notag = notag.replaceAll("[^a-zA-Z ]", "");
			String[] notags = notag.split(" ");
    	
			for (String s : notags) {
				s = s.toLowerCase();
				if (map.containsKey(s)) {
					int count= map.get(s);
					map.put(s, count+1);
				}
				else {
					map.put(s,1);
				}
			}
    	
    		System.out.println(notag);
		}
    	for(Entry<String, Integer> entry: map.entrySet()) {
    		System.out.println(entry);	
    	}
    	
        //Write the workbook in file system
    	File file = new File("/Users/wilsenkosasih/desktop/count.csv"); 
    	// create FileWriter object with file as parameter 
    	FileWriter outputfile = new FileWriter(file); 
      
    	// create CSVWriter object filewriter object as parameter 
    	CSVWriter writer = new CSVWriter(outputfile); 
      
    	// adding header to csv 
    	String[] header = { "String", "Count"}; 
    	writer.writeNext(header); 
      
    	for (String s: map.keySet()) {
            String sa = s;
            String[] data1 = {sa, map.get(sa).toString()};
            writer.writeNext(data1);
    	}
      
    	// closing writer connection 
    	writer.close(); 
    	        
       
        System.out.println("Writesheet.xlsx written successfully");
	}
}