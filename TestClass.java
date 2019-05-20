// Nomaan Khan
// nak150230
// CS 2336-004
// Term Project: Option 2 Dictionary lookup.

import java.io.*;
import java.util.*;

public class TestClass {
    
    @SuppressWarnings({ "resource"})
	public static void main(String[] args) {
    	Scanner in = null;																 // Scanner Variable.
    	try {
			in = new Scanner(new File ("dictionary.txt"));								 // Opening file.
		} 
    	catch (Exception e) {
			System.out.println("File not found.");
		}
    	
    	List<String> l = new ArrayList<String>();
    	
    	while (in.hasNext())															 // Adding word from file to list.
    		l.add(in.next());
    	
    	String[] content = l.toArray(new String[0]);									 // Converting list to array.
    	
    	TrieStruct t = new TrieStruct(content);											 // Initializing TrieStruct object and passing array which has all dictionary words.
    	
    	
    	while(true) {																	 // Loop for user input and operation choice.
    		System.out.println();
    		
    		System.out.println("Enter 1 to add word into trie, 2 for auto complete autoResults, 3 for search word frequency and 4 to exit: ");
        	Scanner scan = new Scanner(System.in);
    		int x = scan.nextInt();
    		
    		switch(x) {
    		case 1: System.out.println("Enter word to add: ");							 // Add word to Trie
    		Scanner scan1 = new Scanner(System.in);
    		String add = scan1.next();
    		t.addWord(add);
    		break;
    		
    		case 2: System.out.println("Enter prefix to autocomplete: ");				 // Auto Complete autoResults for trie.
    		Scanner scan2 = new Scanner(System.in);
    		String auto = scan2.next();
    		
    		List<String> autoResults = t.autoComplete(auto); 							 // List which will contain all auto complete results.
    		
    		System.out.println("Auto complete results: ");
    		Iterator<String> i = autoResults.iterator();
    		while(i.hasNext())															 // Printing list.
    		    System.out.print(i.next()+"\t");
    		break;
    		
    		case 3: System.out.println("Enter word whose frequency you want to find: "); // Finding word frequency in trie.
    		Scanner scan3 = new Scanner(System.in);
    		String look = scan3.next();
    		t.lookupWord(look);
    		break;
    		
    		case 4: System.exit(0);														 // Exiting the program.
    		}
    	}  	
    }  
}