import java.text.ParseException;
import javax.swing.*;


public class FrameElections extends JFrame {
	
	private int x = 400;
	private int y = 200;
	private int width = 500;
	private int height = 300;
	
	private ListenerLogin listenerLogin;
	private ListenerSelection listenerSelection;
	
	private Elections elections;
	

	public FrameElections() throws ParseException {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x,y,width,height);
		
		listenerLogin = new ListenerLogin(this);
		listenerSelection = new ListenerSelection(this);
		elections = new Elections();
		
		PanelLogin panelLogin = new PanelLogin(width,height,listenerLogin);
		add(panelLogin);
	
		setVisible(true);	
	}
	
	
	public ListenerSelection getListenerSelection() {
		return listenerSelection;
	}
	
	
	public Elections getElections() {
		return elections;
	}

}
