package SinglePlayer;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class InGameGUI extends JPanel {
	public JTextArea textArea;
	public InGameGUI() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblYourBoard = new JLabel("Your Board");
		lblYourBoard.setFont(new Font("Courier New", Font.BOLD, 20));
		lblYourBoard.setBounds(56, 42, 175, 24);
		add(lblYourBoard);
		
		JLabel lblGameLogs = new JLabel("Game Logs");
		lblGameLogs.setBounds(40, 570, 158, 30);
		lblGameLogs.setFont(new Font("Courier New", Font.BOLD, 20));
		add(lblGameLogs);
		
		textArea = new JTextArea();
		textArea.setBounds(183, 575, 679, 192);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(183, 575, 681, 194);
		add(scrollPane);
		scrollPane.setViewportView(textArea);
	}
}
