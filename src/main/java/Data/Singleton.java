package Data;

import gui.Gui1;
import gui.Gui2;

public class Singleton {

	static Singleton instance;
	public static MqttVerbindung connection;
	public static Gui1 gui1;
	public static Gui2 gui2;
	public static Verschluesselung verschluesselung;

	private Singleton() {
	}

	public static void main(String[] args) {
		Singleton.getInstance();
		connection = new MqttVerbindung();
//		connection.start();
		gui1 = new Gui1();
		gui2 = new Gui2();
		
		verschluesselung = new Verschluesselung();
		
	}

	public static Singleton getInstance() {
		if (!(Singleton.instance == null)) {
			Singleton.instance = new Singleton();
		}
		return instance;

	}
	
	
	
	
	
	
	
	
}
