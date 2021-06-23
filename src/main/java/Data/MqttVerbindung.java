package Data;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttVerbindung extends Thread {

	public MqttClient client;
	public String topicsub;
	public boolean subscribed;
	
	String[] test = new String[10];
	int laufzahl = 0;
	String ausgabe = "";

	public MqttVerbindung() {
		subscribed = false;
	}

	public void connectClient(String ip, String port, String username, String password) {
		
		
		if (port.matches("1883")) {
			String serverip = "tcp://" + ip + ":" + port;
			try {
				client = new MqttClient(serverip, "Hakan_Server");
				MqttConnectOptions opt = new MqttConnectOptions();
				if (!username.matches("") && !password.matches("")) {
					opt.setUserName(username);
					opt.setPassword(password.toCharArray());
				}
				client.connect(opt);

			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		
		else if (port.matches("8883")) {
			String serverip = "ssl://" + ip + ":" + port;
			try {
				client = new MqttClient(serverip, "Hakan_Server");
				MqttConnectOptions opt = new MqttConnectOptions();
				if (!username.matches("") && !password.matches("")) {
					opt.setUserName(username);
					opt.setPassword(password.toCharArray());
				}
				try {
					opt.setSocketFactory(Verschluesselung.getSocketFactory());
					client.connect(opt);
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
		
		
		
		

		if (client.isConnected()) {
			Singleton.getInstance().connection.start();
			System.out.println("connected to  " + client.getCurrentServerURI().toString());
			
		}

	}

	public void subscribed(String topic) {

		if (client.isConnected()) {
			if (subscribed) {
				try {
					client.unsubscribe(topicsub);
				} catch (MqttException e) {
					e.printStackTrace();
				}
				subscribed = false;
			}
			try {
				client.subscribe(topic);
				topicsub = topic;
				subscribed = true;
			} catch (MqttException e) {
				e.printStackTrace();
			}
			System.out.println("connected to  " + client.getCurrentServerURI().toString());
		}

	}

	public void disconnect() {
//				Singleton.getInstance().connection.stop();
		try {
			client.disconnect();
			client.close();
		} catch (MqttException e) {
			e.printStackTrace();

		}

	}

	public void run() {
		if (client.isConnected()) {
			client.setCallback(new MqttCallback() {

				public void messageArrived(String topic, MqttMessage message) throws Exception {


				
					if (laufzahl == 10 ) {
						System.out.print("10");
						
						for(int i = 0 ; i < test.length-1; i++) {
							test[i] = test[i +1];
						}
						
						laufzahl = 9;
						ausgabe = "";
					}
					
					test[laufzahl] = "Topic: " + topic + " Message: " + message + "\n";
					laufzahl++;
								
					for(int i = 0 ; i < test.length; i++) {
						if (test[i] != null) {
							ausgabe += test[i];
						}
					}
					
					Singleton.getInstance().gui2.txt.setText(ausgabe);
					ausgabe = "";
					
					System.out.println("Topic: " + topic + " Message: " + message);

				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("");

				}

				public void connectionLost(Throwable cause) {
					System.out.println("disconnect，you can reconnect: " + cause);

				}
			});

		}

	}

}
