// Nomaan Khan
// nak150230
// CS 2336-004
// Term Project: Option 2 dictionary lookup.

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

//Trie data structure.
public class TrieStruct {
	// Node of the trie.
	private class Node {
        String prefix; 											// Word up to this node.
        HashMap<Character, Node> childNode;						// A map to connect to child nodes.
        
        boolean isWordEnd;										// Denotes if node is the end of a word.
        public int count = 1; 									// frequency of the word in the trie.
        private Node(String prefix) {							// Constructor to initialize node.
            this.prefix = prefix;
            this.childNode = new HashMap<Character, Node>();
        }
    }
    
    private Node trie;											 // A node of the trie data structure.
    
    // Construct the trie from the contents of the dictionary.
    public TrieStruct(String[] content) {
        trie = new Node("");
        for (String s : content) addWord(s);
    }
    
    // Adds word into the trie if the word already exists in the trie its count is incremented. 
    public void addWord(String s) {
    	
        Node curr = trie; // Root node.
        
       	// Searching for the word in trie and IF the word is found then its
        // count is incremented(in the search function).
        
        if(search(s,1)!=1) {									 // Adding the word to the trie.

        	for (int i = 0; i < s.length(); i++) {				 // Parsing through trie and adding the word letter by letter.
        	
        		if (!curr.childNode.containsKey(s.charAt(i)))	 // Checking if letter is already in trie, if not it's added.
        			curr.childNode.put(s.charAt(i), new Node(s.substring(0, i + 1)));
        		curr = curr.childNode.get(s.charAt(i));
        		if (i == s.length() - 1) 						 // When at end of the word set flag to true.
        			curr.isWordEnd = true;
        	}
        }
    }
    
    // Searches for word.
    public int search(String word, int choice) {
        
        Node current = trie;								 	// Root node.
        for (int i = 0; i < word.length(); i++)	{			 	// Searching for word; parsing node by node.
        
            char ch = word.charAt(i);
            Node node = current.childNode.get(ch);
            
            if (node == null)								 	// If node does not exist for given letter then return 0, because word does not exist.
                return 0;
            
            current = node;
        }
        
        if(choice==1)									     	 // If choice is 1 then the user has chosen to add the word and 
        	if(current.isWordEnd) {				    		  	 // it already exists in the trie, so its count is incremented.
        	
        		current.count++;
        		return 0;
        	}
        
        return current.count;							   // The user has chosen to find the count of the word, which is returned.
    }
    
    // Find all words in trie that start with prefix
    public List<String> autoComplete(String pre) {
        List<String> autoResults = new LinkedList<String>();
        
        Node current = trie;
        for (char c : pre.toCharArray()) {						// Loop to check if the prefix is in the trie.
        
            if (current.childNode.containsKey(c)) 
                current = current.childNode.get(c);
            
            else
                return autoResults;
        }
        
        recursiveWordRecovery(current, autoResults); 			// To get all the words that have the entered prefix.
        return autoResults;
    }
    
    // Recursively find every child word with the entered prefix.
    private void recursiveWordRecovery(Node n, List<String> autoResults) {
        
        if (n.isWordEnd) 										// Checking if we are at a word and if we are then we add it to autoResults.
        	autoResults.add(n.prefix);
        for (Character c : n.childNode.keySet()) 				// Loop to recursively go through children and them to the list.
        	recursiveWordRecovery(n.childNode.get(c), autoResults);
    }
    
    // Finds the count of word and prints it by performing a search for it.
    public void lookupWord(String word) {

    	int count = search(word,0);
    	System.out.println();
    	System.out.println("This word appears "+count+" time(s) in the trie.");
    }
}