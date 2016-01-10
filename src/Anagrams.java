import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Anagrams {
	List<String> words;
	List<String> listOfAnagrams = new ArrayList();
	
	public static void main(String args[]) throws IOException{
		new Anagrams().run();
	}

	private void run() throws IOException{
		words = loadListOfWords();
		String word = JOptionPane.showInputDialog("Please enter in a word.");
		findAnagrams(word, words);
	}

	private void findAnagrams(String word, List<String> words) {
		for(int i = 0;i<words.size();i++){
			if(isAnagram(word,words.get(i))==true){
				listOfAnagrams.add(words.get(i));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		String message = "";
		
		for(String w: listOfAnagrams){
			if(!first){
				sb.append(", ");
			}
			else{
				first = false;
			}
		sb.append(w);
		}
		message = sb.toString();
		JOptionPane.showMessageDialog(null, message);
	}

	private boolean isAnagram(String a, String b) {
		char[] aChar = new char[a.length()];
		char[] bChar = new char[b.length()];
		
		if(a==null||b==null||a.length()!=b.length()){
			return false;
		}
		else{
			a = a.toLowerCase();
			b = b.toLowerCase();
			aChar = sortLetters(a);
			bChar = sortLetters(b);
			if(Arrays.equals(aChar, bChar)){
				return true;
			}
			else{
				return false;
			}
		}
	}

	private char[] sortLetters(String str) {
		char[] abChar = new char[str.length()];
		abChar = str.toCharArray();
		Arrays.sort(abChar);
		return abChar;
	}

	private List<String> loadListOfWords() throws IOException {
		List<String> listOfWords = new ArrayList();
		URL url = new URL ("https://raw.githubusercontent.com/eneko/data-repository/master/data/words.txt");
		URLConnection connection = url.openConnection();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			listOfWords = reader.lines().collect(Collectors.toList());
		}
		return listOfWords;
	}

}
