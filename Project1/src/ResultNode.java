/****
 *  Nicole Lee (ncl2108), Laima Tazmin (lt2233)
 *	E6111 - Project 1
 *	02/24/11
 *	This class stores information about a single result and its user-defined relevance.
 ****/

import java.io.IOException;
import java.util.ArrayList;

public class ResultNode {

	private int docid = -1; // tracks the document id
	private String title;
	private String url;
	private String summary;
	private boolean relevant = false; // Tracks if the result is relevant
	private ArrayList<String> terms = new ArrayList<String>();
	
	/**
	 * Constructor for result node
	 * @param summary Result abstract
	 * @param title Result title
	 * @param url Result URL
	 */
//	public ResultNode (String summary, String title, String url) {
//		this.title = trim(title);
//		this.url = trim(url);
//		this.summary = trim(summary);
//	}

	/**
	 * Constructor for result node
	 * @param docid Document number
	 * @param summary Result summary
	 * @param title Result title
	 * @param url Result URL
	 */
	public ResultNode (int docid, String summary, String title, String url) {
		this.docid = docid;
		this.title = trim(title);
		this.url = trim(url);
		this.summary = trim(summary);
		setTerms(); // create the keywords list
	}

	public int getDocId () {
		return docid;
	}
	
	/**
	 * Sets the relevance of a result
	 * @param bool a String that is "N" or "Y"
	 */
	public void setRelevance (String bool) {
		if (bool.equalsIgnoreCase("Y"))
			relevant = true;
		else
			relevant = false;
	}
	
	/**
	 * Returns if a result is relevant or not
	 * @return A boolean false or true
	 */
	public boolean isRelevant () {
		return relevant;
	}
	
	/**
	 * Returns result's url
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<String> getTerms() {
		return terms;
	}
	/**
	 * Returns result's web page
	 * @return
	 */
	public String getWebPage() throws IOException {
		return YahooSearchBOSS.urlGet(url);
	}
	
	/**
	 * Removes HTML code and replaces escaped characters
	 * @param str A string from Yahoo
	 * @return A clean string
	 */
	public static String trim(String str) {
		String newStr = null;

		newStr = str.replaceAll("\\<.*?b\\>", "");
		newStr = newStr.replaceAll("\\\\/", "/");
		newStr = newStr.replaceAll("\\\\\"", "\"");

		return newStr;
	}
	
	/**
	 * Tokenize the title and summary
	 * store the terms in keywords
	 */
	public void setTerms () {
		String text = title + " " + summary;
		String[] words = text.split(" ");
		for (int pos = 0; pos < words.length; pos++) {
			String word = words[pos];
			word = word.replaceAll("^\\W+", ""); // trim non-word characters before word
			word = word.replaceAll("\\W+$", ""); // trim non-word characters after word
			word = word.toLowerCase();  // lower case the word
			if (word.equals("")) continue;
			terms.add(word);
		}
	}

	/**
	 * Prints the node
	 */
	public String toString() {
		String str = String.format("[\n URL: %s\n Title: %s\n Summary: %s\n]\n", url, title, summary);
		return str;
	}
}

