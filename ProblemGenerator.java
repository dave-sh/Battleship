package SinglePlayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class ProblemGenerator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProblemGenerator frame = new ProblemGenerator();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static String questionDifficulty = MainMenu.selectedQuesDifficulty;
	static int qDifficulty;
	static Random rndm = new Random();
	Problems p = null;
	int x;
	public ProblemGenerator() {
		if(questionDifficulty.equals("Easy")) {
			qDifficulty = 0;
		}else if(questionDifficulty.equals("Medium")) {
			qDifficulty = 1;
		}else if(questionDifficulty.equals("Hard")) {
			qDifficulty = 2;
		}
		x = rndm.nextInt(3);
		System.out.println(x);
		if(x == 0) {
			p = Problems.addition(qDifficulty);
		}else if(x == 1) {
			p = Problems.subtraction(qDifficulty);
		}else if(x == 2) {
			p = Problems.multiplication(qDifficulty);
		}else if(x == 3) {
			p = Problems.division(qDifficulty);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(50, 138, 162, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Input an answer!", "Error", 0);
				}else if(Integer.parseInt(textField.getText()) == p.answer){
					JFrame frame = (JFrame) SwingUtilities.getRootPane(contentPane).getParent();
					frame.dispose();
					JOptionPane.showMessageDialog(null, "Correct!", "Continue!", 3);
				}else {
					JOptionPane.showMessageDialog(null, "Wrong!", "Try again!", 0);
				}
			}
		});
		btnSubmit.setBounds(249, 147, 97, 25);
		contentPane.add(btnSubmit);
		
		JLabel lblQuestionTime = new JLabel("Question Time!");
		lblQuestionTime.setBounds(50, 32, 188, 16);
		contentPane.add(lblQuestionTime);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(50, 76, 217, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(p.question);
	}
	
}
