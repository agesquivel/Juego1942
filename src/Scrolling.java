import javax.swing.JFrame;

//import javax.microedition.midlet.*; 
//import javax.microedition.lcdui.*;

public class Scrolling extends JFrame /*MIDlet implements CommandListener*/  
{
	//private Command exitCommand; 
	//private Display display; 
	private SSCanvas screen;

	public Scrolling() {
		//display=Display.getDisplay(this);
		//exitCommand = new Command("Salir",Command.SCREEN,2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 600);
		
		screen=new SSCanvas();
		screen.setFocusable(true);
		this.add(screen);
		this.setVisible(true);
		//screen.addCommand(exitCommand); 
		//screen.setCommandListener(this);
		//this.addKeyListener(screen);
		
		new Thread(screen).start(); 
	}
	
	/*public void startApp() throws MIDletStateChangeException { 
		display.setCurrent(screen);
	}*/
	
	//public void pauseApp() {}
	
	//public void destroyApp(boolean unconditional) {}
	
	/* public void commandAction(Command c, Displayable s) {
		if (c == exitCommand) { 
			destroyApp(false); notifyDestroyed();
		} 
	} */

	public static void main(String[] args) {
		Scrolling ventana = new Scrolling();
	}
}


