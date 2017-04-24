import java.util.Random;
import java.util.Scanner;

/**
 * This program is used to play the game pig against 
 * 'AI' (very loose term). 
 * 
 *Start Date:2016/09/27 
 *Last Updated:2016/09/29
 *Tom Szendrey, 10187030, Assignment1, pig game
 */
public class Assignment1 {

	public static void main(String[] args){
		int[] scores = {0,0}; //total scores, where scores[0] is the humans score, and scores[1] is the computers score
		playerTurn(0,scores); //The user has first turn.
		
		
	}//end of main
	
	/**
	 * The playerTurn method will act as the player's turn
	 * This class will ask the user if they would like to roll again, or stop.
	 */
	public static void playerTurn(int turnScore, int[] scores){
		int[] dieVals = roll();
		System.out.println("You have rolled: " + dieVals[0] + " " + dieVals[1]);
		//The user has the choice the go again or not.
		if (dieVals[0] != 1 && dieVals[1] != 1){
			turnScore = turnScore + dieVals[0] + dieVals[1];
			int playerOption = printOptions(dieVals,turnScore, scores[0]);
			if (playerOption == 1){
				playerTurn(turnScore,scores);
			}
			else if (playerOption == 2){
				scoreTracker(turnScore,scores,true,false);
			}
			else{
				System.out.print("That wasnt a valid option");
				printOptions(dieVals,turnScore,scores[0]);
			}
		}//ends if dieVals != 1
		//the user's turn is over, and their total score is reset to 0.
		else if (dieVals[0] == 1 && dieVals[1] == 1){
			scores[0] = 0;
			scoreTracker(0,scores,true, false);
			
		} // ends else if (both dice roll 1)	
		//only one 1 was rolled, turnScore is set to 0, and playerTurn is over.
		else{
			scoreTracker(0,scores,true, false);
			
		}// ends else
	}// playerTurn ends
	
	/**
	 * the roll method will be called with no arguments,
	 * and return 2 random numbers between 1-6.
	 */
	public static int[] roll(){
		int[] dieValues = new int[2]; 
		Random rand = new Random();
		int temp = rand.nextInt(6) + 1;
		Random rand1 = new Random();
		int temperary = rand1.nextInt(6) + 1;
		dieValues[0] = temp;
		dieValues[1] = temperary;
		return(dieValues); // change this to two random numbers when you look up random numbers.
		
	}
	/*
	 * this will keep track of the scores, and who is going next.
	 */
	public static void scoreTracker(int turnScore, int[] scores, boolean humanTurn, boolean humanTurnNext){
		//totalScore = turnScore + totalScore;
		if (humanTurn == true){
			scores[0] = scores[0] + turnScore;
			if (humanTurnNext == true){
				playerTurn(turnScore,scores);
			}
			else{
				computerTurn(0,scores);
			}
		}
		else{
			scores[1] = scores[1] + turnScore;
			if (humanTurnNext == false){
				computerTurn(turnScore,scores);
			}
			else{
				playerTurn(0,scores);
			}
		}
		
	} // ends scoreTracker
	
	private static void computerTurn(int turnScore, int[] scores) {
		if (turnScore >= 30){ //if it gets enough points in the turn, it passes his turn
			scoreTracker(turnScore, scores, false, true);
		}
		else{
			int[] dieVals = roll();
			turnScore = turnScore + dieVals[0] + dieVals[1];
			System.out.println("The computer has rolled " + dieVals[0] + " " + dieVals[1] + " and has");
			System.out.println("Total score of: " + scores[1] + "\nTurn score of: " + turnScore);
			if (dieVals[0] != 1 && dieVals[1] != 1){
				computerTurn(turnScore,scores);
			}
			//the user's turn is over, and their total score is reset to 0.
			else if (dieVals[0] == 1 && dieVals[1] == 1){
				scores[0] = 0;
				scores[1] = 0;
				scoreTracker(0,scores,false, true);
			} // ends else if (both dice roll 1)	
			//only one 1 was rolled, turnScore is set to 0, and computer's turn is over.
			else{
				scoreTracker(0,scores,false, true);
			}
		}
	}
	
	/**
	 * This is used to print out the player's options
	 * @param dieVals -to let them know what they rolled
	 * @param turnScore -to let them know their turn's score
	 * @param humanScore -to let them know their total score.
	 * @return - their choice (to continue playing, or to pass their turn)
	 */
	public static int printOptions(int [] dieVals, int turnScore, int humanScore){
		Scanner reader = new Scanner(System.in);
		System.out.println("Your total score is: " + humanScore + "\nYour turn's score is: " + turnScore);
		System.out.print("Would you like to play or pass your turn? (Y/N) \n");
		int playerChoice = reader.nextInt(); //currently takes numbers, not srtings... shh
		return(playerChoice); //user_in.nextLine();
	}
}// class ends
