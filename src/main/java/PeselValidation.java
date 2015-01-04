import java.lang.Character;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PeselValidation {

	private String pesel;
	
	
	public PeselValidation(String pesel) {
		this.pesel = pesel;
	}
	
	
	public boolean is11Digits() {
		int length = pesel.length();
		
		if(length != 11) {
			return false;
		}
		
		char sign;
		
		for(int i = 0; i < length; i++) {
			sign = pesel.charAt(i);
			if(Character.isDigit(sign) == false) {
				return false;
			}
		}
		
		return true;
	}


	public boolean isCorrect() {		
		int a = toDigit(pesel.charAt(0));
		int b = toDigit(pesel.charAt(1));
		int c = toDigit(pesel.charAt(2));
		int d = toDigit(pesel.charAt(3));
		int e = toDigit(pesel.charAt(4));
		int f = toDigit(pesel.charAt(5));
		int g = toDigit(pesel.charAt(6));
		int h = toDigit(pesel.charAt(7));
		int i = toDigit(pesel.charAt(8));
		int j = toDigit(pesel.charAt(9));
		int k = toDigit(pesel.charAt(10));
		
		int result = a + 3*b + 7*c + 9*d + e + 3*f + 7*g + 9*h + i + 3*j + k;
		
		if(result == 0) {
			return false;
		}
		
		if(result % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	
	private int toDigit(char character) {
		int asciiCode = (int) character;
		int digit = asciiCode - 48;
		
		return digit;
	}


	public String getSex() {
		int digitSex = toDigit(pesel.charAt(9));
		
		if(digitSex % 2 == 0) {
			return "female";
		} else {
			return "male";
		}
	}


	public String getDateOfBirth() {
		String year = pesel.substring(0,2);
		String month = pesel.substring(2,4);
		String day = pesel.substring(4,6);
		
		String date;
		
		char firstDigitOfMonth = month.charAt(0);
		int firstDigit = toDigit(firstDigitOfMonth);
		
		if((firstDigit == 0) || (firstDigit == 1)) {
			date = "19" + year + "." + month + "." + day;
		} else if((firstDigit == 2) || (firstDigit == 3)) {
			String newMonth = toNewMonth(firstDigit,2,month);
			date = "20" + year + "." + newMonth + "." + day;
		} else if((firstDigit == 4) || (firstDigit == 5)) {
			String newMonth = toNewMonth(firstDigit,4,month);
			date = "21" + year + "." + newMonth + "." + day;
		} else if((firstDigit == 6) || (firstDigit == 7)) {
			String newMonth = toNewMonth(firstDigit,6,month);
			date = "22" + year + "." + newMonth + "." + day;
		} else {
			String newMonth = toNewMonth(firstDigit,8,month);
			date = "18" + year + "." + newMonth + "." + day;
		}
		
		return date;
	}
	
	
	private String toNewMonth(int firstDigit, int number, String month) {
		int newFirstDigit = firstDigit - number;
		String newMonth = "" + newFirstDigit + month.charAt(1);
		
		return newMonth;
	}


	public Date getDateOfBirthAsDate() throws ParseException {
		String dateString = getDateOfBirth();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date date = dateFormat.parse(dateString);		
	
		return date;	
	}


	public boolean isAdult(Date dateOfElection) throws ParseException {
		String dateOfBirth = getDateOfBirth();		
		String majority = getDateOfMajority(dateOfBirth);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateOfMajority = dateFormat.parse(majority);		
	
		if(dateOfMajority.after(dateOfElection) == true) {
			return false;
		} else {
			return true;
		}	
	}
	
	
	private String getDateOfMajority(String dateOfBirth) {
		String yearOfBirth = dateOfBirth.substring(0,4); 
		
		int year1 = toDigit(yearOfBirth.charAt(0));
		int year2 = toDigit(yearOfBirth.charAt(1));
		int year3 = toDigit(yearOfBirth.charAt(2));
		int year4 = toDigit(yearOfBirth.charAt(3));
	
		int year = 1000*year1 + 100*year2 + 10*year3 + year4;
		int yearOfMajority = year + 18;
		
		String dateOfMajority = yearOfMajority + dateOfBirth.substring(4);
		return dateOfMajority;
	}
	
}
