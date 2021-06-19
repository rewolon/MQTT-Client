package Data;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttConnection extends Thread {

	MqttClient client;
	public MqttConnection() {	
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

	public void run() {
		if (client.isConnected()) {
			client.setCallback(new MqttCallback() {

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					System.out.println("Topic: " + topic + " Message: " + message);

				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub

				}

				public void connectionLost(Throwable cause) {
					// TODO Auto-generated method stub

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
//	                //sampleClient.disconnect();
//	                //System.out.println("Disconnected");
//	                //System.exit(0);
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
