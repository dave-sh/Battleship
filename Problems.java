package SinglePlayer;
import java.util.Scanner;
import java.util.Random;
public class Problems {
	public String question;
	public int answer;
	public static int id;
	private static int num1;
	private static int num2;
	private static Random rndm = new Random();
	
	//So a random can be used to generate problems
	public static final int ADD_ID = 0;
	public static final int SUBTRACT_ID = 1;
	public static final int MULTIPLY_ID = 2;
	public static final int DIVIDE_ID = 3;
	
	//question level of difficulty
	private static final int DIFFICULTY_EASY = 0;
	private static final int DIFFICULTY_MEDIUM = 1;
	private static final int DIFFICULTY_HARD = 2;
	
	private static int numberRange;
	
	//constructor
	public Problems(String q, int a, int i){
		this.question = q;
		this.answer = a;
		Problems.id = i;
	}
	
	//generate addition problem
	public static Problems addition(int i){
		int difficulty = i;
		if(difficulty == DIFFICULTY_EASY) {
			numberRange = 10;
		}else if(difficulty == DIFFICULTY_MEDIUM) {
			numberRange = 15;
		}else if(difficulty == DIFFICULTY_HARD) {
			numberRange = 25;
		}
		num1 = rndm.nextInt(numberRange)+1;
		num2 = rndm.nextInt(numberRange)+1;
		int ans = num1+num2;
		String question = num1 +" + " + num2 +" = ?";
		Problems p = new Problems(question, ans, 0);
		return p;
	}
	
	//generate subtraction problem
	public static Problems subtraction(int i){
		int difficulty = i;
		if(difficulty == DIFFICULTY_EASY) {
			numberRange = 10;
		}else if(difficulty == DIFFICULTY_MEDIUM) {
			numberRange = 15;
		}else if(difficulty == DIFFICULTY_HARD) {
			numberRange = 25;
		}
		num1 = rndm.nextInt(numberRange)+1;
		num2 = rndm.nextInt(numberRange)+1;
		int ans = num1-num2;
		String question = num1 +" - " + num2 +" = ?";
		Problems p = new Problems(question, ans, 0);
		return p;
	}
	
	//generate multiplication problem
	public static Problems multiplication(int i){
		int difficulty = i;
		if(difficulty == DIFFICULTY_EASY) {
			numberRange = 10;
		}else if(difficulty == DIFFICULTY_MEDIUM) {
			numberRange = 15;
		}else if(difficulty == DIFFICULTY_HARD) {
			numberRange = 20;
		}
		num1 = rndm.nextInt(numberRange)+1;
		num2 = rndm.nextInt(numberRange)+1;
		int ans = num1*num2;
		String question = num1 +" * " + num2 +" = ?";
		Problems p = new Problems(question, ans, 0);
		return p;
	}
	
	//generate division problem
	public static Problems division(int i){
		int difficulty = i;
		if(difficulty == DIFFICULTY_EASY) {
			numberRange = 10;
		}else if(difficulty == DIFFICULTY_MEDIUM) {
			numberRange = 15;
		}else if(difficulty == DIFFICULTY_HARD) {
			numberRange = 20;
		}
		num1 = rndm.nextInt(numberRange)+1;
		num2 = rndm.nextInt(numberRange)+1;
		int dividend = num1*num2;
		String question = dividend +" / " + num2 +" = ?";
		Problems p = new Problems(question, num1, 0);
		return p;
	}
	
	//debugging
	public static void main(String args[]){
		Problems p = Problems.division(1);
		System.out.println(p.question);
	}
}
