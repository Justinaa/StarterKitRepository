import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.fest.assertions.Assertions;
import org.junit.Test;


public class PeselValidationTest {

	private PeselValidation peselValidation;	
	private String pesel;
	
	String peselWoman = "90051808627";
	String peselMan = "58041403418";
	
	
	
	//---testing-is11Digits()----------------------------------------------------
	
	@Test
	public void is11DigitsShouldReturnTrueFor11Digits() {
		//given
		pesel = "12345678901";
		peselValidation = new PeselValidation(pesel);
		
		//when
		boolean result = peselValidation.is11Digits();
		
		//then
		Assertions.assertThat(result).isEqualTo(true);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor10Digits() {
		//given
		pesel = "1234567890";
		peselValidation = new PeselValidation(pesel);
		
		//when
		boolean result = peselValidation.is11Digits();
		
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor12Digits() {
		//given
		pesel = "123456789012";
		peselValidation = new PeselValidation(pesel);
		
		//when
		boolean result = peselValidation.is11Digits();
		
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor11Letters() {
		//given
		pesel = "abcdefghijk";
		peselValidation = new PeselValidation(pesel);
		
		//when
		boolean result = peselValidation.is11Digits();
		
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor10DigitsAndLetterK() {
		//given
		pesel = "1234567890k";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.is11Digits();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor10DigitsAndLetterA() {
		//given
		pesel = "a2345678901";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.is11Digits();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor10DigitsAndSpace() {
		//given
		pesel = "123 5678901";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.is11Digits();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor10DigitsAndCharAscii58() {
		//given
		pesel = "1:35678901";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.is11Digits();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnFalseFor10DigitsAndCharAscii47() {
		//given
		pesel = "12356789/1";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.is11Digits();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void is11DigitsShouldReturnTrueForCorrectPesel() {
		//given
		pesel = peselWoman;
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.is11Digits();
				
		//then
		Assertions.assertThat(result).isEqualTo(true);
	}

	//---------------------------------------------------------------------------
	
	
	//---testing-isCorrect()-----------------------------------------------------
	
	@Test
	public void isCorrectShouldReturnTrueForCorrectPesel() {
		//given
		pesel = peselWoman;
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.isCorrect();
				
		//then
		Assertions.assertThat(result).isEqualTo(true);
	}
	
	
	@Test
	public void isCorrectShouldReturnFalseForIncorrectPesel() {
		//given
		pesel = "44051401358";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.isCorrect();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	
	@Test
	public void isCorrectShouldReturnFalseFor00000000000() {
		//given
		pesel = "00000000000";
		peselValidation = new PeselValidation(pesel);
				
		//when
		boolean result = peselValidation.isCorrect();
				
		//then
		Assertions.assertThat(result).isEqualTo(false);
	}
	
	//---------------------------------------------------------------------------
	
	
	//---testing-getSex()--------------------------------------------------------
	
	@Test
	public void getSexShouldReturnFemaleForWoman() {
		//given
		pesel = peselWoman;
		peselValidation = new PeselValidation(pesel);
				
		//when
		String sex = peselValidation.getSex();
				
		//then
		Assertions.assertThat(sex).isEqualTo("female");
	}
	
	
	@Test
	public void getSexShouldReturnMaleForMan() {
		//given
		pesel = peselMan;
		peselValidation = new PeselValidation(pesel);
				
		//when
		String sex = peselValidation.getSex();
				
		//then
		Assertions.assertThat(sex).isEqualTo("male");
	}	
	
	//---------------------------------------------------------------------------
	
	
	//---testing-getDateOfBirth()------------------------------------------------
	
	@Test
	public void getDateOfBirthShouldReturn19900518ForYear1990() {
		//given
		pesel = peselWoman;
		peselValidation = new PeselValidation(pesel);
				
		//when
		String date = peselValidation.getDateOfBirth();
				
		//then
		Assertions.assertThat(date).isEqualTo("1990.05.18");
	}	
	
	
	@Test
	public void getDateOfBirthShouldReturn18000518ForYear1800() {
		//given
		pesel = "00851801358";
		peselValidation = new PeselValidation(pesel);
				
		//when
		String date = peselValidation.getDateOfBirth();
				
		//then
		Assertions.assertThat(date).isEqualTo("1800.05.18");
	}	
	
	
	@Test
	public void getDateOfBirthShouldReturn20141128ForYear2014() {
		//given
		pesel = "14312801358";
		peselValidation = new PeselValidation(pesel);
				
		//when
		String date = peselValidation.getDateOfBirth();
				
		//then
		Assertions.assertThat(date).isEqualTo("2014.11.28");
	}	
	
	
	@Test
	public void getDateOfBirthShouldReturn21990103ForYear2199() {
		//given
		pesel = "99410301358";
		peselValidation = new PeselValidation(pesel);
				
		//when
		String date = peselValidation.getDateOfBirth();
				
		//then
		Assertions.assertThat(date).isEqualTo("2199.01.03");
	}	
	
	
	@Test
	public void getDateOfBirthShouldReturn22011220ForYear2201() {
		//given
		pesel = "01722001358";
		peselValidation = new PeselValidation(pesel);
				
		//when
		String date = peselValidation.getDateOfBirth();
				
		//then
		Assertions.assertThat(date).isEqualTo("2201.12.20");
	}	
	
	//---------------------------------------------------------------------------
	
	
	//---testing-getDateOfBirthAsDate()------------------------------------------
	
	@Test
	public void getDateOfBirthAsDateShouldReturn22011220ForYear2201() throws ParseException {
		//given
		pesel = "01722001358";
		peselValidation = new PeselValidation(pesel);
				
		//when
		Date date = peselValidation.getDateOfBirthAsDate();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateVerification = dateFormat.parse("2201.12.20");	
		
		//then
		Assertions.assertThat(date).isEqualTo(dateVerification);
	}	
	
	//---------------------------------------------------------------------------
	
	
	//---testing-isAdult()-------------------------------------------------------
	
	@Test
	public void isAdultShouldReturnTrueForAdult() throws ParseException {
		//given
		pesel = peselWoman;
		peselValidation = new PeselValidation(pesel);
				
		//when
		String dateOfElectionString = "2015.01.02";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateOfElection = dateFormat.parse(dateOfElectionString);
		
		boolean isAdult = peselValidation.isAdult(dateOfElection);
			
		//then
		Assertions.assertThat(isAdult).isEqualTo(true);
	}
	
	
	@Test
	public void isAdultShouldReturnFalseForUnderAge() throws ParseException {
		//given
		pesel = "14312801358"; 		//"2014.11.28"
		peselValidation = new PeselValidation(pesel);
				
		//when
		String dateOfElectionString = "2015.01.02";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateOfElection = dateFormat.parse(dateOfElectionString);
		
		boolean isAdult = peselValidation.isAdult(dateOfElection);
			
		//then
		Assertions.assertThat(isAdult).isEqualTo(false);
	}
	
	
	@Test
	public void isAdultShouldReturnTrueForAdultFromDateOfElection() throws ParseException {
		//given
		pesel = "14312801358"; 		//"2014.11.28"
		peselValidation = new PeselValidation(pesel);
				
		//when
		String dateOfElectionString = "2032.11.28";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateOfElection = dateFormat.parse(dateOfElectionString);
		
		boolean isAdult = peselValidation.isAdult(dateOfElection);
			
		//then
		Assertions.assertThat(isAdult).isEqualTo(true);
	}
	
	
	@Test
	public void isAdultShouldReturnFalseForAdultFromDateOfElectionPlus1() throws ParseException {
		//given
		pesel = "14312801358"; 		//"2014.11.28"
		peselValidation = new PeselValidation(pesel);
				
		//when
		String dateOfElectionString = "2032.11.27";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateOfElection = dateFormat.parse(dateOfElectionString);
		
		boolean isAdult = peselValidation.isAdult(dateOfElection);
			
		//then
		Assertions.assertThat(isAdult).isEqualTo(false);
	}
	
	
	@Test
	public void isAdultShouldReturnTrueForAdultFromDateOfElectionMinus1() throws ParseException {
		//given
		pesel = "14312801358"; 		//"2014.11.28"
		peselValidation = new PeselValidation(pesel);
				
		//when
		String dateOfElectionString = "2032.11.29";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date dateOfElection = dateFormat.parse(dateOfElectionString);
		
		boolean isAdult = peselValidation.isAdult(dateOfElection);
			
		//then
		Assertions.assertThat(isAdult).isEqualTo(true);
	}
	
	//---------------------------------------------------------------------------
	
}

