package Data;

	
			import java.sql.Timestamp;

			import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
			import org.eclipse.paho.client.mqttv3.MqttCallback;
			import org.eclipse.paho.client.mqttv3.MqttClient;
	        import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
	        import org.eclipse.paho.client.mqttv3.MqttException;
	        import org.eclipse.paho.client.mqttv3.MqttMessage;
	        import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

	        public class MqttConnection {

	        public static void main(String[] args) {

	            String topic        = "MQTT Examples";
	            String content      = "Message from MqttPublishSample";
	            int qos             = 2;
	            String portAnonymUnverschl	= "1883";
	            String broker       = "tcp://test.mosquitto.org:" +  portAnonymUnverschl;
	            String clientId     = "JavaSample";
	            MemoryPersistence persistence = new MemoryPersistence();

	            try {
	                MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	                MqttConnectOptions connOpts = new MqttConnectOptions();
	                connOpts.setCleanSession(true);
	                
	                sampleClient.setCallback(new MqttCallback() {

	                    public void messageArrived(String topic, MqttMessage message) throws Exception {
	                        String time = new Timestamp(System.currentTimeMillis()).toString();
	                        System.out.println("\nReceived a Message!" +
	                            "\n\tTime:    " + time +
	                            "\n\tTopic:   " + topic +
	                            "\n\tMessage: " + new String(message.getPayload()) +
	                            "\n\tQoS:     " + message.getQos() + "\n");
	                        //latch.countDown(); // unblock main thread
	                    }

	                    public void connectionLost(Throwable cause) {
	                        System.out.println("Connection to Solace broker lost!" + cause.getMessage());
	                        //latch.countDown();
	                    }

	                    public void deliveryComplete(IMqttDeliveryToken token) {
	                    }

	                });
	                
	                
	                
	                
	                
	                
	                
	                
	                System.out.println("Connecting to broker: "+broker);
	                sampleClient.connect(connOpts);
	                System.out.println("Connected");
	                System.out.println("Publishing message: "+content);
	                MqttMessage message = new MqttMessage(content.getBytes());
	                message.setQos(qos);
	                
	                sampleClient.subscribe(topic);
	                
	                sampleClient.publish(topic, message);
	                System.out.println("Message published");
	                //sampleClient.disconnect();
	                //System.out.println("Disconnected");
	                //System.exit(0);
	            } catch(MqttException me) {
	                System.out.println("reason "+me.getReasonCode());
	                System.out.println("msg "+me.getMessage());
	                System.out.println("loc "+me.getLocalizedMessage());
	                System.out.println("cause "+me.getCause());
	                System.out.println("excep "+me);
	                me.printStackTrace();
	                
	            }
	        }
	    }
	
	
	

