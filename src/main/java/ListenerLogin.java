import java.awt.event.*;
import javax.swing.JOptionPane;


public class ListenerLogin implements ActionListener {
	
	private FrameElections frameElections;
	private PanelLogin panelLogin;
	private PanelSelection panelSelection;
	
			
	public ListenerLogin(FrameElections frameElections) {
		this.frameElections = frameElections;	
	}
	
	
	public void actionPerformed(ActionEvent event) {			
		String pesel = this.panelLogin.getTextField().getText(); 
		boolean isPeselCorrect = isPeselCorrect(pesel);
					
		if(isPeselCorrect) {		
			boolean isVoterAdult = isVoterAdult(pesel);		
				
			if(isVoterAdult) {
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
	}
	
	
	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}
	
	
	private boolean isPeselCorrect(String pesel) {
		boolean isCorrect = false;
		PeselValidation peselValidation = new PeselValidation(pesel);
		
		if(peselValidation.is11Digits()) {
			if(peselValidation.isCorrect()) {
				isCorrect = true;
			}
		}
		
		return isCorrect;
	}
	
	
	private boolean isVoterAdult(String pesel) {
		PeselValidation peselValidation = new PeselValidation(pesel);
		
		if(peselValidation.isAdult()) {
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
