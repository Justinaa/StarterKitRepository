import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


public class ListenerLogin implements ActionListener {
	
	private FrameElections frameElections;
	private PanelLogin panelLogin;
	private PanelSelection panelSelection;
	
	//---do-zmiany---ma-być-pobierane-z-bazy-danych--------------------------------------
	private String dateOfElection = "2015.02.14";
	//-----------------------------------------------------------------------------------
	
			
	public ListenerLogin(FrameElections frameElections) {
		this.frameElections = frameElections;	
	}
	
	
	public void actionPerformed(ActionEvent event) {			
		String pesel = this.panelLogin.getTextField().getText(); 
		boolean isPeselCorrect = isPeselCorrect(pesel);
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		try {			
			if(isPeselCorrect == true) {
				
				//---throws-ParseException-----------------
				boolean isVoterAdult = isVoterAdult(pesel);		
				//-----------------------------------------
				
				if(isVoterAdult == true) {
					String zipCode = this.panelLogin.getComboBox().getSelectedItem().toString();
			
					Elections elections = frameElections.getElections();
					elections.setPesel(pesel);
					elections.setZipCode(zipCode);
		
					panelChange(zipCode);
				} else {
					JOptionPane.showMessageDialog(null,"Osoba niepełnoletnia nie może brać udziału w wyborach!","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null,"Nieprawidłowy numer PESEL!","Error",JOptionPane.ERROR_MESSAGE);
			}
		} catch(ParseException exception) {
			System.out.println(exception.toString());
		}
		//------------------------------------------------------------------------------------------------------------------------------------------
	}
	
	
	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}
	
	
	private boolean isPeselCorrect(String pesel) {
		boolean isCorrect = false;
		PeselValidation peselValidation = new PeselValidation(pesel);
		
		if(peselValidation.is11Digits() == true) {
			if(peselValidation.isCorrect() == true) {
				isCorrect = true;
			}
		}
		
		return isCorrect;
	}
	
	
	private boolean isVoterAdult(String pesel) throws ParseException {
		PeselValidation peselValidation = new PeselValidation(pesel);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date election = dateFormat.parse(dateOfElection);
		
		if(peselValidation.isAdult(election) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	private void panelChange(String zipCode) {
		panelSelection = new PanelSelection(frameElections.getListenerSelection());
		
		frameElections.getContentPane().removeAll();
		frameElections.add(panelSelection);
		frameElections.getContentPane().revalidate();
		
		frameElections.setTitle("Okręg wyborczy "+zipCode);
	}

}
