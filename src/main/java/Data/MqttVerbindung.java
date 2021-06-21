package Data;

import java.util.Iterator;

import javax.swing.text.StyledDocument;

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
		String serverip = "tcp://" + ip + ":" + port;

		try {
			client = new MqttClient(serverip, "HAKIMODE");
			MqttConnectOptions opt = new MqttConnectOptions();
			if (!username.matches("") && !password.matches("")) {
				opt.setUserName(username);
				opt.setPassword(password.toCharArray());
			}
			client.connect(opt);

		} catch (MqttException e) {
			e.printStackTrace();
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

/*
 * Array	Wert
 * 0    =	1
 * 1 	= 	2
 * 2 	=	3
 * 3 	= 	4				Erster Schritt
 * 4 	= 	5
 * 5 	= 	6
 * 6 	= 	7
 * 7 	=	8
 * 8 	= 	9
 * 9 	= 	10
 * 
 * Array	Wert
 * 0    =	2
 * 1 	= 	3
 * 2 	=	4
 * 3 	= 	5				nach laufzahl 10
 * 4 	= 	6
 * 5 	= 	7
 * 6 	= 	8
 * 7 	=	9
 * 8 	= 	10
 * 9 	= 	
 * 
 * Array	Wert
 * 0    =	2
 * 1 	= 	3
 * 2 	=	4
 * 3 	= 	5
 * 4 	= 	6
 * 5 	= 	7
 * 6 	= 	8
 * 7 	=	9
 * 8 	= 	10
 * 9 	= 	11
 * 
 * 
 * 
 * 					
 */
				
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
//
//	        public static void main(String[] args) {
//
//	            String topic        = "MQTT Examples";
//	            String content      = "Message from MqttPublishSample";
//	            int qos             = 2;
//	            String portAnonymUnverschl	= "1883";
//	            String broker       = "tcp://test.mosquitto.org:" +  portAnonymUnverschl;
//	            String clientId     = "JavaSample";
//	            MemoryPersistence persistence = new MemoryPersistence();
//
//	            try {
//	                MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//	                MqttConnectOptions connOpts = new MqttConnectOptions();
//	                connOpts.setCleanSession(true);
//	                
//	                sampleClient.setCallback(new MqttCallback() {
//
//	                    public void messageArrived(String topic, MqttMessage message) throws Exception {
//	                        String time = new Timestamp(System.currentTimeMillis()).toString();
//	                        System.out.println("\nReceived a Message!" +
//	                            "\n\tTime:    " + time +
//	                            "\n\tTopic:   " + topic +
//	                            "\n\tMessage: " + new String(message.getPayload()) +
//	                            "\n\tQoS:     " + message.getQos() + "\n");
//	                        //latch.countDown(); // unblock main thread
//	                    }
//
//	                    public void connectionLost(Throwable cause) {
//	                        System.out.println("Connection to Solace broker lost!" + cause.getMessage());
//	                        //latch.countDown();
//	                    }
//
//	                    public void deliveryComplete(IMqttDeliveryToken token) {
//	                    }
//
//	                });
//	                
//	                
//	
//	
//	                
//	                System.out.println("Connecting to broker: "+broker);
//	                sampleClient.connect(connOpts);
//	                System.out.println("Connected");
//	                System.out.println("Publishing message: "+content);
//	                MqttMessage message = new MqttMessage(content.getBytes());
//	                message.setQos(qos);
//	                
//	                sampleClient.subscribe(topic);
//	                
//	                sampleClient.publish(topic, message);
//	                System.out.println("Message published");
//	                sampleClient.disconnect();
//	                System.out.println("Disconnected");
//	                System.exit(0);
//	            } catch(MqttException me) {
//	                System.out.println("reason "+me.getReasonCode());
//	                System.out.println("msg "+me.getMessage());
//	                System.out.println("loc "+me.getLocalizedMessage());
//	                System.out.println("cause "+me.getCause());
//	                System.out.println("excep "+me);
//	                me.printStackTrace();
//	                
//	            }
//	        }
}
