package SinglePlayer;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import battleshipNetwork.MultiplayerGUI;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainMenu extends JFrame {
	//TODO Add clear ships button
	//TODO Multiplayer
	private static final long serialVersionUID = -8630953642276609206L;
	
	static JFrame frame;
	private JPanel contentPane;
	
	//Class variables to share information with other classes
	public static String selectedCpuDifficulty;
	public static String selectedQuesDifficulty;
	public static int selectedGridSize;
	public static String currentDirection = ">>>>";
	static JButton btnSingleConfirm;
	public static String selectedShip = "";
	public static int difficultySelected;
	public static MainMenu main;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main = MainMenu.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static JButton btnBattleship, btnAircraftCarrier, btnSubmarine, btnDestroyer, btnCruiser, btnRotate;
	//Initializing JFrame items
	JLabel lblCpuDifficulty, lblQuestionDifficulty, player1Label, player2Label;
	JButton singleplayerbtn, twoPlayerbtn;

	static JButton btnDifficulty;

	JButton btnQuestionDifficulty;
	String[] directions = {">>>>", "<<<<", "^^^^", "vvvv"};
	String[] difficulties = {"Disabled", "Easy", "Medium", "Hard"};
	String[] cpuDifficulties = {"Easy", "Medium", "Hard"};
	String[] boardSizes = {"10 x 10", "12 X 12", "14 x 14"};
	int difficulty = 0, questionDifficulty = 0, gridSize = 0, direction = 0;
	Color backgroundColor = new Color(112, 146, 190);
	
	//Instance fields
	Player player1;
	Computer cpu;
	InGameGUI contentPane2;
	JTextArea gamelogs;
    Grid player1board;
	
	public MainMenu(Player player1, InGameGUI content, Computer cpu, JTextArea gamelogs) {
		this.player1 = player1;
		this.cpu = cpu;
		contentPane2 = content;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 850);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		btnDifficulty = new JButton("Easy");
		btnDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(difficulty == 2){
					difficulty = 0;
				}else{
					difficulty++;
				}
				btnDifficulty.setText(cpuDifficulties[difficulty]);
			}
		});
		btnDifficulty.setBounds(618, 419, 170, 46);
		contentPane.add(btnDifficulty);
		
		btnQuestionDifficulty = new JButton("Disabled");
		btnQuestionDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(questionDifficulty == 3){
					questionDifficulty = 0;
				}else{
					questionDifficulty++;
				}
				btnQuestionDifficulty.setText(difficulties[questionDifficulty]);
			}
		});
		btnQuestionDifficulty.setBounds(800, 419, 217, 46);
		contentPane.add(btnQuestionDifficulty);
		
		lblCpuDifficulty = new JLabel("CPU Difficulty");
		lblCpuDifficulty.setBounds(618, 392, 81, 14);
		contentPane.add(lblCpuDifficulty);
		
		lblQuestionDifficulty = new JLabel("Question Difficulty");
		lblQuestionDifficulty.setBounds(800, 392, 125, 14);
		contentPane.add(lblQuestionDifficulty);
		
		btnBattleship = new JButton("Battleship");
		btnBattleship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedShip = "battleship";
				System.out.println(selectedShip);
			}
		});
		btnBattleship.setBounds(755, 130, 170, 46);
		
		btnAircraftCarrier = new JButton("Aircraft Carrier");
		btnAircraftCarrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedShip = "carrier";
				System.out.println(selectedShip);
			}
		});
		btnAircraftCarrier.setBounds(947, 130, 170, 46);
		
		btnDestroyer = new JButton("Destroyer");
		btnDestroyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedShip = "destroyer";
				System.out.println(selectedShip);
			}
		});
		btnDestroyer.setBounds(755, 189, 170, 46);
		
		btnSubmarine = new JButton("Submarine");
		btnSubmarine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedShip = "submarine";
				System.out.println(selectedShip);
			}
		});
		btnSubmarine.setBounds(947, 189, 170, 46);
		
		btnCruiser = new JButton("Cruiser");
		btnCruiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedShip = "cruiser";
			}
		});
		btnCruiser.setBounds(855, 248, 170, 46);
		
		singleplayerbtn = new JButton("Single Player");
		singleplayerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Images.initImages();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame = (JFrame) SwingUtilities.getRootPane(contentPane).getParent();
				selectedGridSize = 10;
				selectedQuesDifficulty = btnQuestionDifficulty.getText();
				difficultySelected = getDifficulty(btnDifficulty);
				contentPane.removeAll();
				contentPane.setBackground(backgroundColor);
				frame.setContentPane(contentPane2);
				player1board = player1.createBoard(contentPane2, player1, 0, 65, 10);
				contentPane2.add(btnAircraftCarrier);
				contentPane2.add(btnDestroyer);
				contentPane2.add(btnSubmarine);
				contentPane2.add(btnCruiser);
				contentPane2.add(btnBattleship);
				contentPane2.add(btnSingleConfirm);
				contentPane2.add(btnRotate);
				contentPane2.validate();
				contentPane2.repaint();
				frame.revalidate();
				frame.repaint();
			}
		});
		singleplayerbtn.setBounds(248, 419, 119, 46);
		contentPane.add(singleplayerbtn);
		
		btnSingleConfirm = new JButton("Confirm");
		btnSingleConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!btnAircraftCarrier.isEnabled() && !btnDestroyer.isEnabled() && !btnSubmarine.isEnabled() && !btnBattleship.isEnabled() && !btnCruiser.isEnabled()) {
					JFrame parentFrame = (JFrame) SwingUtilities.getRootPane(btnCruiser).getParent();
					parentFrame.getContentPane().remove(btnAircraftCarrier);
					parentFrame.getContentPane().remove(btnBattleship);
					parentFrame.getContentPane().remove(btnSubmarine);
					parentFrame.getContentPane().remove(btnDestroyer);
					parentFrame.getContentPane().remove(btnCruiser);
					parentFrame.getContentPane().remove(btnSingleConfirm);
					parentFrame.getContentPane().remove(btnRotate);
					parentFrame.getContentPane().setBackground(backgroundColor);
					JLabel lblOpponentBoard = new JLabel("Opponent Board");
					lblOpponentBoard.setFont(new Font("Courier New", Font.BOLD, 20));
					lblOpponentBoard.setBounds(610, 39, 204, 30);
					parentFrame.getContentPane().add(lblOpponentBoard);
					Computer cpu2; 
					if(getDifficulty(btnDifficulty) == 1) {
						cpu2 = new Computer(5, 1, cpu.shipSetup(), false, false, getDifficulty(btnDifficulty), true, cpu.scanBroke(player1board.getGrid()));
					}else {
						cpu2 = new Computer(5, 1, cpu.shipSetup(), false, false, getDifficulty(btnDifficulty), true, cpu.scan(player1board.getGrid()));
					}
					Grid cpuGrid = cpu2.createGrid((JPanel) parentFrame.getContentPane(), cpu, 550, 65, player1, player1board, 10);
					cpu2.gridMask(cpuGrid.getGrid());
					parentFrame.getContentPane().revalidate();
					parentFrame.getContentPane().repaint();
					System.out.println("Started");
					player1.setPlaying(true);
					System.out.println(player1.isPlaying());
					cpu.setPlaying(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "Not all ships have been placed.");
				}
			}
		});
		btnSingleConfirm.setBounds(855, 300, 100, 70);
		
		twoPlayerbtn = new JButton("Multiplayer");
		twoPlayerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame)SwingUtilities.getRootPane(contentPane).getParent();
				contentPane.removeAll();
				contentPane.setBackground(backgroundColor);
				MultiplayerGUI.initialize();
				frame.dispose();
			}
		});
		twoPlayerbtn.setBounds(418, 419, 119, 46);
		contentPane.add(twoPlayerbtn);
		
		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setForeground(Color.BLACK);
		lblBattleship.setFont(new Font("Courier New", Font.BOLD, 50));
		lblBattleship.setBounds(418, 196, 321, 57);
		contentPane.add(lblBattleship);
		
		JLabel lblGameModes = new JLabel("Game Modes");
		lblGameModes.setBounds(248, 391, 153, 16);
		contentPane.add(lblGameModes);
		
		btnRotate = new JButton(">>>>");
		btnRotate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(direction == 3) {
					direction = 0;
				}else {
					direction++;
				}
				btnRotate.setText(directions[direction]);
				currentDirection = btnRotate.getText();
			}
		});
		btnRotate.setBounds(945, 300, 100, 70);
	}
	
	public static int getDifficulty(JButton btn){
		if(btn.getText().equals("Easy")){
			return 0;
		}else if(btn.getText().equals("Medium")){
			return 1;
		}else if(btn.getText().equals("Hard")){
			return 2;
		}else if(btn.getText().equals("Cheater")){
			return 3;
		}
		return 4;
	}
	
	public static MainMenu initialize() {
		InGameGUI contentPane2 = new InGameGUI();
		Player playerInit = new Player(5, 0);
		Player player1 = new Player(5, 0, playerInit.shipSetup(), false, false, false);
		Computer cpuInit = new Computer(5, 1);
		Computer cpu = new Computer(5, 1, cpuInit.shipSetup(), false, false, 1, true);
		MainMenu frame = new MainMenu(player1, contentPane2, cpu, contentPane2.textArea);
		frame.setVisible(true);
		frame.setTitle("Battleship");
		return frame;
	}
}


