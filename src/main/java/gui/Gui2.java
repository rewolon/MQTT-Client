package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Data.Singleton;

public class Gui2 {

	private JPanel contentPane;
	public JPanel panelTopicNachrichten;
	public JTextPane txt;
	
	

//	public static void NeuerScreen() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Gui2 frame = new Gui2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public void init() {
		final JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("MQTT-Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setBounds(0, 0, screenSize.width, screenSize.height);

		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTopics = new JPanel();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Topics");
		title.setTitleJustification(TitledBorder.CENTER);
		panelTopics.setBorder(title);

		// panelTopics.setBorder(BorderFactory.createTitledBorder(blackline, "Topics"));
		// Für Titel auf der Linken seite

		panelTopics.setBounds(0, 0, frame.getWidth() * 1 / 4, frame.getHeight() * 1 / 3);

		contentPane.add(panelTopics);

		int w = panelTopics.getWidth();
		int h = panelTopics.getHeight();

		panelTopics.setLayout(null);

		final JRadioButton rdbtnTemperature = new JRadioButton("Temperature");
		rdbtnTemperature.setBounds(w / 8, h * 8 / 36, 110, 25);
		rdbtnTemperature.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Singleton.getInstance().connection.subscribed(rdbtnTemperature.getText());
			}
		});
		panelTopics.add(rdbtnTemperature);

		final JRadioButton rdbtnPressure = new JRadioButton("Pressure");
		rdbtnPressure.setBounds(w / 8, h * 4 / 9, 110, 25);
		rdbtnPressure.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Singleton.getInstance().connection.subscribed(rdbtnPressure.getText());
			}
		});
		panelTopics.add(rdbtnPressure);

		final JRadioButton rdbtnHumidity = new JRadioButton("Humidity");
		rdbtnHumidity.setBounds(w / 8, h * 2 / 3, 110, 25);
		rdbtnHumidity.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Singleton.getInstance().connection.subscribed(rdbtnHumidity.getText());
			}
		});
		panelTopics.add(rdbtnHumidity);

		final JRadioButton rdbtnAccelleration = new JRadioButton("Accelleration");
		rdbtnAccelleration.setBounds(w * 4 / 6, h * 8 / 36, 110, 25);
		rdbtnAccelleration.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Singleton.getInstance().connection.subscribed(rdbtnAccelleration.getText());
			}
		});
		panelTopics.add(rdbtnAccelleration);

		final JRadioButton rdbtnGyrodata = new JRadioButton("Gyrodata");
		rdbtnGyrodata.setBounds(w * 4 / 6, h * 4 / 9, 110, 25);
		rdbtnGyrodata.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Singleton.getInstance().connection.subscribed(rdbtnGyrodata.getText());
			}
		});
		panelTopics.add(rdbtnGyrodata);

		final JRadioButton rdbtnMagdata = new JRadioButton("Magdata");
		rdbtnMagdata.setBounds(w * 4 / 6, h * 2 / 3, 110, 25);
		rdbtnMagdata.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Singleton.getInstance().connection.subscribed(rdbtnMagdata.getText());
			}
		});
		panelTopics.add(rdbtnMagdata);

		ButtonGroup groupe = new ButtonGroup();
		groupe.add(rdbtnTemperature);
		groupe.add(rdbtnPressure);
		groupe.add(rdbtnHumidity);
		groupe.add(rdbtnAccelleration);
		groupe.add(rdbtnGyrodata);
		groupe.add(rdbtnMagdata);

		JButton btnVerbindungTrennen = new JButton("Verbindung trennen");
		btnVerbindungTrennen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//				Gui2 schließen, Gui1 wieder öffnen, mosquitto.org disconnecten

				
				
				Singleton.getInstance().connection.disconnect();
				System.out.println("disconnected");
				Singleton.getInstance().gui1.frmMQTTLogin.setVisible(true);
				frame.dispose();
				
				
				
//				this.setVisible(false);
//				sampleClient.disconnect();
//				System.out.println("Disconnected");
//				System.exit(0);
//				JFrame.dispose();
//				this.dispose();
				
				
				

			}
		});
		btnVerbindungTrennen.setBounds(w * 12 / 100, h * 88 / 100, w * 3 / 4, 25);
		panelTopics.add(btnVerbindungTrennen);

		panelTopicNachrichten = new JPanel();
		
		txt = new JTextPane();
		
		panelTopicNachrichten.setBounds(0, frame.getHeight() / 3, frame.getWidth() / 4, frame.getHeight() * 2 / 3 - 30);
		contentPane.add(panelTopicNachrichten);
		panelTopicNachrichten.setLayout(null);
		
		

		
		Border blackline2 = BorderFactory.createLineBorder(Color.black);
		TitledBorder title2 = BorderFactory.createTitledBorder(blackline2, "Die letzten 10 Nachrichten");
		title2.setTitleJustification(TitledBorder.CENTER);
		
//		panelTopicNachrichten.setBorder(title2);
		
//		txt.setBorder(title2);

		txt.setSize(panelTopicNachrichten.getSize());
		txt.setEditable(false);
		panelTopicNachrichten.add(txt);
		
		txt.setText("start");
		


		JPanel panelGraph = new JPanel();
		panelGraph.setBounds(frame.getWidth() * 1 / 4, 0, frame.getWidth() * 3 / 4 - 6, frame.getHeight() - 30);
		contentPane.add(panelGraph);
		panelGraph.setLayout(null);

		Border blackline3 = BorderFactory.createLineBorder(Color.black);
		TitledBorder title3 = BorderFactory.createTitledBorder(blackline3, "Graph");
		title3.setTitleJustification(TitledBorder.CENTER);
		panelGraph.setBorder(title3);
		
		frame.setVisible(true) ;
	

	}
	
	public void setTTT(String a) {
		System.out.println(a);
		txt.setText("IMINININASDINIAWDNIWNDIAWDINAWDAUWDAUDH");
		
	}
}
