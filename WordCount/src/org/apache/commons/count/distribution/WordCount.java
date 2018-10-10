package org.apache.commons.count.distribution;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class WordCount{
	public static void main(String[] args) throws IOException {
		//using hash map for storing words
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		Scanner txtFile = new Scanner(new File("C:\\cs5180\\count.txt"));
		while (txtFile.hasNext()) {
			String Word = txtFile.next();
			if (map.containsKey(Word)) {
				int count= map.get(Word);
				map.put(Word, count);
			}
			else {
				map.put(Word,1);
			}
		}
		
		txtFile.close();
		for(Entry<String, Integer> entry: map.entrySet()) {
		System.out.println(entry);	
		}
	}
}