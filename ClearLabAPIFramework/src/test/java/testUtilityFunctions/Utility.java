package testUtilityFunctions;

import java.util.Random;

public class Utility 
{
	public static Random rnd = new Random();

	public static String getRandomNumber(int digCount) 
	{
	    StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(10)));
	    return sb.toString();
	}
	
	public static String getRandomString(int stringCount) 
	{    
	    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    
	    for (int i = 0; i < stringCount; i++) {
	        char c = chars[random.nextInt(chars.length)];
	        sb.append(c);
	    }
	    return sb.toString();
	}
}
