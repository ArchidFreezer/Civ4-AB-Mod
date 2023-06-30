package org.archid.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	static Pattern WORD_FINDER = Pattern.compile("(([A-Z]?[a-z]+)|([A-Z]))");
	
/**
	 * Determine if a string contains any text or is null
	 * 
	 * @param s String to be examined.
	 * 
	 * @return true if the string is non-null and has at least 1 character in it.
	 */
	public static boolean hasCharacters(String s) {
		return !isNullOrEmpty(s);
	}

	/**
	 * Determine if a string is empty or null.
	 * 
	 * @param s String to be examined.
	 * 
	 * @return true if the String is null or has length == 0. false otherwise.
	 */
	public static boolean isNullOrEmpty(String s) {
		if ((s == null) || (s.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Converts a string value to null if it is empty
	 * 
	 * @param s String to be examined
	 * 
	 * @return <code>null</code> if the string is already null or empty; otherwise the unchanged string
	 */
	public static String getNullIfEmpty(String s) {
		return isNullOrEmpty(s) ? null : s;
	}

	/**
	 * Convert String to Start Case i.e.
	 * capitalise first letter of every word to upper case
	 * "THIS_TEXT" -> "This_Text"
	 * 
	 * @param str {@code String} to parse
	 * @param delim {@code char} delimiting the words
	 * 
	 * @return String converted to Start Case
	 */
	public static String titleCase(String str, char delim)
	{
		StringBuilder builder = new StringBuilder(str.toLowerCase());
		
		str = str.toLowerCase();
		
		// Flag to keep track if last visited character is a white space or not
		boolean isLastSpace = true;
		
		// Iterate String from beginning to end.
		for(int i = 0; i < builder.length(); i++)
		{
			char ch = builder.charAt(i);
			
			if(isLastSpace)
			{
				// Character need to be converted to uppercase
				builder.setCharAt(i, Character.isLetter(ch) ? (char)(ch + ('A' - 'a') ) : ch);
				isLastSpace = false;
			}
			else if (ch != delim)
				isLastSpace = false;
			else
				isLastSpace = true;
		}
	
		return builder.toString();
	}

	/**
	 * Convert given String to Start Case, removing the word delimiter i.e.
	 * Capitalise first letter of every word to upper case
	 * "THIS_TEXT" -> "ThisText"
	 * 
	 * @param str {@code String} to parse
	 * @param delim {@code char} delimiting the words
	 * 
	 * @return String converted to Start Case
	 */
	public static String titleCaseCompress(String str, char delim)
	{
		StringBuilder input = new StringBuilder(str.toLowerCase());
		StringBuilder output = new StringBuilder();
		
		str = str.toLowerCase();
		
		// Flag to keep track if last visited character is a white space or not
		boolean isLastSpace = true;
		
		// Iterate String from beginning to end.
		for(int i = 0; i < input.length(); i++)
		{
			char ch = input.charAt(i);
			
			if(isLastSpace)
			{
				// Character need to be converted to uppercase
				output.append(Character.isLetter(ch) ? (char)(ch + ('A' - 'a') ) : ch);
				isLastSpace = false;
			}	else if (ch != delim) {
				output.append(ch);
				isLastSpace = false;
			} else {
				isLastSpace = true;
			}
		}
	
		return output.toString();
	}

	/**
	 * Convert Given String to Camel Case, replacing the word delimiter with a space i.e.
	 * Capitalise first letter of every word to upper case
	 * "THIS_TEXT" -> "This Text"
	 * 
	 * @param str {@code String} to parse
	 * @param delim {@code char} delimiting the words
	 * 
	 * @return String converted to Start Case with delimiter converted to a space
	 */
	public static String titleCaseSpace(String str, char delim)
	{
		StringBuilder input = new StringBuilder(str.toLowerCase());
		StringBuilder output = new StringBuilder();
		
		// Flag to keep track if last visited character is a white space or not
		boolean isLastSpace = true;
		
		// Iterate String from beginning to end.
		for(int i = 0; i < input.length(); i++)
		{
			char ch = input.charAt(i);
			
			if(isLastSpace)
			{
				// Character need to be converted to uppercase if it is a letter
				output.append(Character.isLetter(ch) ? (char)(ch + ('A' - 'a') ) : ch);
				isLastSpace = false;
			}	else if (ch != delim) {
				output.append(ch);
				isLastSpace = false;
			} else {
				output.append(' ');
				isLastSpace = true;
			}
		}
	
		return output.toString();
	}
	
	/**
	 * Convert given string to upper case with word boundaries split by the delimiter, i.e.
	 * "ThisIsAPieceOfText" -> "THIS_IS_A_PIECE_OF_TEXT"
	 * 
	 * @param str {@code String} to parse
	 * @param delim {@code char} delimiting the words
	 * 
	 * @return String converted to uppercase with words divided by the delimiter
	 */
	public static String uCaseSplit(String str, char delim) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String word: findWordsInMixedCase(str)) {
			if (first)
				first = false;
			else
				sb.append(delim);
			sb.append(word.toUpperCase());
		}
		return sb.toString();
	}
	
	public static String padValue(Integer val, int padlen, char padChar) {
		StringBuilder sb = new StringBuilder(String.valueOf(val));
		while (padlen - sb.length() > 0) {
			sb.insert(0, padChar);
		}
		return sb.toString();
	}
	
	public static String lCaseFirstChar(String val) {
		return val.substring(0,1).toLowerCase() + val.substring(1);
	}

	public static String uCaseFirstChar(String val) {
		return val.substring(0,1).toUpperCase() + val.substring(1);
	}

	/**
	 * Takes a string in the form of separate words, camelCase or TitleCase and converts it to a list
	 * of the individual words.
	 *  
	 * @param text value to parse
	 * @return List<String> of words in the param
	 */
	public static List<String> findWordsInMixedCase(String text) {
    Matcher matcher = WORD_FINDER.matcher(text);
    List<String> words = new ArrayList<>();
    while (matcher.find()) {
        words.add(matcher.group(0));
    }
    return words;
	}

	public static boolean startsWithLowerCase(String val) {
		return val.matches("[a-z].*");
	}
}
