package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//Question 1:
//----------
//
//There is a file containing a word and its possible meanings (like a Dictionary). The contents of the file look like this:
//
//Apple – a fruit, a tech firm
//Table – an object, contains rows and columns when used in context of computers
//Orange – a fruit
//
//Given a path to the file, do the following:
//
//a)    Create a method called doesFileExist(String path) which takes the path of the file and tells the user if the file exists at that path or not. Assume all paths are relative to your project structure. If the file does not exist, catch the requisite exception.
//b)    Read each word and its possible meanings and print them out. Your output should look like this:
//
//Word1
//Meaning 1
//Meaning 2
//Word2
//Meaning1
//Meaning2
//
//Use appropriate data structures wherever necessary.


public class Exercise1 {
	// to check whether the file exists or not
	boolean doesFileExist(String path) {
		File file = new File(path);
		return file.exists();

	}
	//to print each word and its meaning
	void printEachWordAndMeaning(String path) throws FileNotFoundException {
		File file = new File(path);
		HashMap<String, List<String>> hm = new HashMap<String, List<String>>();

		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] strList = line.split(" – ");
			String name = strList[0];
			String meanings[] = strList[1].split(", ");
			List<String> ll = Arrays.asList(meanings);
			hm.put(name, ll);
		}

		for (java.util.HashMap.Entry<String, List<String>> entry : hm.entrySet()) {
			System.out.println(entry.getKey());
			for (String meaning : entry.getValue()) {
				System.out.println(meaning);
			}

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Exercise1 exercise1 = new Exercise1();
		String path = System.getProperty("user.dir");
		path = path + "\\Exercise1File.txt";
		exercise1.printEachWordAndMeaning(path);
	}
}
