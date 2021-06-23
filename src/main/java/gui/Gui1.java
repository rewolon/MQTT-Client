package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Data.Singleton;

public class Gui1 extends Thread {

	public JFrame frmMQTTLogin;
	private JTextField txtUsername;
	private JTextField txtPasswort;
	private JTextField txtServerIP;
	private JTextField txtPort;



	public Gui1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMQTTLogin = new JFrame();
		frmMQTTLogin.setResizable(false);
		frmMQTTLogin.setTitle("MQTT-Login");
		frmMQTTLogin.setBounds(100, 100, 400, 400);
		frmMQTTLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMQTTLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(30, 30, 70, 20);
		frmMQTTLogin.getContentPane().add(lblUsername);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setBounds(30, 70, 70, 20);
		frmMQTTLogin.getContentPane().add(lblPasswort);
		
		JLabel lblServerIP = new JLabel("Server IP:");
		lblServerIP.setHorizontalAlignment(SwingConstants.LEFT);
		lblServerIP.setBounds(30, 110, 70, 20);
		frmMQTTLogin.getContentPane().add(lblServerIP);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(30, 150, 70, 20);
		frmMQTTLogin.getContentPane().add(lblPort);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(110, 30, 240, 20);
		frmMQTTLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPasswort = new JPasswordField();
		txtPasswort.setColumns(10);
		txtPasswort.setBounds(110, 70, 240, 20);
		frmMQTTLogin.getContentPane().add(txtPasswort);
		
		txtServerIP = new JTextField();
		txtServerIP.setColumns(10);
		txtServerIP.setBounds(110, 110, 240, 20);
		txtServerIP.setText("test.mosquitto.org");
		frmMQTTLogin.getContentPane().add(txtServerIP);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(110, 150, 240, 20);
		txtPort.setText("1883");
		frmMQTTLogin.getContentPane().add(txtPort);
		
		JRadioButton rdbtnUnverschluesselt = new JRadioButton("unverschlüsselt");
		rdbtnUnverschluesselt.setBounds(30, 200, 130, 20);
		rdbtnUnverschluesselt.setSelected(true);
		frmMQTTLogin.getContentPane().add(rdbtnUnverschluesselt);
		
		rdbtnUnverschluesselt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPort.setText("1883");
			}
		}); 
	
		
		
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(rdbtnUnverschluesselt);
		
		
		JRadioButton rdbtnVerschluesselt = new JRadioButton("verschlüsselt");
		rdbtnVerschluesselt.setBounds(30, 235, 130, 20);
		frmMQTTLogin.getContentPane().add(rdbtnVerschluesselt);
		groupe.add(rdbtnVerschluesselt);
		rdbtnVerschluesselt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPort.setText("8883");
			}
		});
		
		
		
		JButton btnVerbinden = new JButton("Verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtServerIP.getText().matches("") && !txtPort.getText().matches("")) {
					Singleton.getInstance().connection.connectClient(txtServerIP.getText(),
							txtPort.getText(), txtUsername.getText(), txtPasswort.getText());
				}
								
				
				Singleton.getInstance().gui2.init();
				
				frmMQTTLogin.setVisible(false);
				
				
			}
		});
		btnVerbinden.setBounds(30, 330, 150, 25);
		frmMQTTLogin.getContentPane().add(btnVerbinden);
		frmMQTTLogin.setVisible(true);
	}
}
