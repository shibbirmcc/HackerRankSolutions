package hackerrank.implementation;

import java.util.Scanner;

public class TheTimeInWords {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int h = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		int m = scanner.nextInt();
		scanner.close();
		
		
		if(m == 0) {
			System.out.println(hourToString(h)+" o' clock");
		}else {
			System.out.println( minuteToString(m) + ( m > 30 ? " to "+ hourToString(h+1) : " past "+ hourToString(h) ));
		}
		
	}
	
	private static String hourToString(int hour) {
		if(hour > 12 ) {
			return singleDigitToString(1);
		}else if(hour < 10 ) {
			return singleDigitToString(hour);
		}else{
			return tenthDigitsToString(hour);
		}
	}
	
	private static String minuteToString(int minute) {
		minute = minute > 30 ? 60 - minute : minute;
		
		if(minute == 30 ) {
			return "half";
		}else if(minute == 15) {
			return "quarter";
		}else if(minute < 10 ) {
			return singleDigitToString(minute)+ (minute > 1 ? " minutes" : " minute") ;
		}else if(minute <= 20) {
			return tenthDigitsToString(minute)+" minutes";
		}else {
			return "twenty "+singleDigitToString(minute-20)+" minutes";
		}
	}


	private static String tenthDigitsToString(int digits) {
		switch (digits) {
		case 10:
			return "ten";
		case 11:
			return "eleven";
		case 12:
			return "twelve";
		case 13:
			return "thirteen";
		case 14:
			return "fourteen";
		case 15:
			return "fifteen";
		case 16:
			return "sixteen";
		case 17:
			return "seventeen";
		case 18:
			return "eighteen";
		case 19:
			return "nineteen";
		case 20:
			return "twenty";
		}
		return "";
	}

	private static String singleDigitToString(int digit) {
		switch (digit) {
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		}
		return "";
	}
}
