import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Paragraph {
	public static String changeDateFormat(String paragraph) {
		String finalString="";
		String[] array = paragraph.split("[\\s]");
		
        String patternString1 = "\\d\\d/\\d\\d/\\d\\d\\d\\d(\\p{Punct})*";

        Pattern pattern = Pattern.compile(patternString1);

		for (String word: array) {
	        Matcher matcher = pattern.matcher(word);

	        if (matcher.matches()){
	        	//convert to date format
	        	String mm = word.substring(0,2);
	        	String dd = word.substring(3,5);
	        	String yyyy = word.substring(6);
	        	word = dd+"/"+mm+"/"+yyyy;
	        }
	        
        	if (finalString.isEmpty()){
        		finalString += word;
        	}
        	else{
        		finalString += " " + word;
        	}

		}
		return finalString;
	}

	public static void main(String[] args) {
		System.out
				.println(changeDateFormat("Last time it rained was on 07/25/2013 and today is 08/09/2013."));
	}
}