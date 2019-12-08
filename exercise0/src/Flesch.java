package oop_hw0_q2;
import java.io.*;
import java.util.Scanner;


public class Flesch {
	/**
	    * @modifies none
	    * @effects calculates the flesch value of a given input file, number of sentences, vowels, and words
	    * @return flesch value, vowels, sentence, and words count
	    */ 	
	public static void main(String args[]) throws IOException { 
		
		checkArguments(args);
		File file = new File(args[0]);
        if(!file.exists() || file.isDirectory()) {
            System.out.println("Error: file does not exist or is a directory");
            System.exit(0);
        }
        
		String wordDelimeter = "[\\.\\,\\'\\:\\;\\?\\{\\}\\[\\]\\=\\-\\_\\!\\+\\@\\#\\$\\%\\^\\&\\*\\(\\)\\s]";
        Scanner ScanFile0 = new Scanner(file); 
        ScanFile0.useDelimiter("[\\.\\,\\;\\?\\!]"); 
        String curSentence = ".";
        int sentenceCounter = 0 ; 
        
        while (ScanFile0.hasNext()){
        	curSentence = ScanFile0.next().trim();
		    sentenceCounter += 1 ; 	
        }
        if (curSentence.length()==0 ){
        	sentenceCounter -= 1;
        }
        	
        int wordCounter =0 ;
        int totalVowelsCounter= 0;
        Scanner ScanFile1 = new Scanner(file);
        ScanFile1.useDelimiter(wordDelimeter);
        while (ScanFile1.hasNext()){
        	String word = ScanFile1.next().trim();
        	if (word.length()!=0  ){
	        	wordCounter +=1;
	        	int vowelsCounter= 0;
	        	Character preC = '.';
	        	int i = 1 ;
	        	for (char f : word.toCharArray()){
	        		char c = Character.toLowerCase(f);
	        		
	        		if (c == 'e' || c == 'a' || c == 'o' || c == 'y' || c == 'u'|| c == 'i'){
	        			if (preC == 'e' || preC == 'a' || preC == 'o' || preC == 'y' || preC == 'u' || preC == 'i'){
	        			}
	        			else{
	        				if (c == 'e' && i == word.length() ){
	        				}
	        				else{
	        					vowelsCounter+=1;
	        				}
	        			}
	        		}
	        		
	        		i +=1 ;
	        		preC= c;
	        		
	        		
	        	}
	        	if (vowelsCounter == 0 ){
	        		vowelsCounter += 1 ;
	        	}
	        	totalVowelsCounter += vowelsCounter ;	
        	}
        }//while ends
        
       double  result = calculate(wordCounter,sentenceCounter,totalVowelsCounter);
       System.out.println("The words number: " + wordCounter);
       System.out.println("The sentences number: " + sentenceCounter);
       System.out.println("The vowels number: " + totalVowelsCounter);
       System.out.println("The flesch is: " + result);
       ScanFile1.close();
       ScanFile0.close();
        
	}// //main ends
	
	
    /**
   	 * @requires wordsNum !=0, linesNum !=0
        * @modifies none
        * @effects calculates the flesch value given  wordsNum, linesNum, vowelsNum
        * @return flesch value
        */ 		 
	private static  double calculate (int wordsNum, int linesNum, int vowelsNum){
		double result = ((double) (84.6*vowelsNum/wordsNum));
		result += ((double) (1.015*wordsNum/linesNum));
		return ((double)(206.835-result));
	}
	
	
    /**
        * @effects checks if arguments given are valid arguments
        */ 
	private static void checkArguments (String[] arguments){
        if (arguments.length>1) {
            System.out.println("Error: More than 1 argument.");
            System.exit(0);
        }
        else if (arguments.length==0){
            System.out.println("Error: There is no filename.");
            System.exit(0);
        }
	}

}
