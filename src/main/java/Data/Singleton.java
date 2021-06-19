package Data;

import gui.Gui1;
import gui.Gui2;

public class Singleton {
	
	static Singleton instance;
	public static MqttConnection connection;
	static Gui1 gui1;
	Gui2 gui2;
	
	private Singleton() {
	}
	public static void main(String[] args) {
		Singleton.getInstance();
		connection = new MqttConnection();
		gui1 = new Gui1();
	}
	public static Singleton getInstance() {
		if (!(Singleton.instance == null)) {
			Singleton.instance = new Singleton();
		}
		return instance;
	
	}
}
