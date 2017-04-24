import java.util.Random;
import java.util.Scanner;

public class assignment1SecondSave {
/**
 * This program is used to play the game pig against 
 * 'AI' (very loose term). 
 * 
 *Start Date:2016/09/27 
 *Last Updated:2016/10/04
 *Tom Szendrey, 10187030, Assignment1, pig game
 */
	public static void main(String[] args){
		int[] scores = {0,0}; //total scores, where scores[0] is the humans score, and scores[1] is the computers score
		playerTurn(0,scores); //The user has first turn.
		
		
	}//end of main
	
	/*
	 * The playerTurn method will act as the player's turn
	 * This class will ask the user if they would like to roll again, or stop.
	 */
	public static void playerTurn(int turnScore, int[] scores){
		int[] dieVals = roll(); //gets 2 values into a list using roll()
		System.out.println("You have rolled: " + dieVals[0] + " " + dieVals[1]);
		//The user has the choice the go again or not.
		if (dieVals[0] != 1 && dieVals[1] != 1){ //no ones were rolled, game continues normally 
			turnScore = turnScore + dieVals[0] + dieVals[1]; //update turnScore
			if (scores[0] + turnScore >= 100){ //check if the game is over
				System.out.println("You have won!");
				System.exit(10); 
			}//ends game over check
			playerOptions(dieVals,turnScore,scores); //get the user's input to continue or pass
		}//ends if dieVals != 1
		//the user's turn is over, and their total score is reset to 0.
		else if (dieVals[0] == 1 && dieVals[1] == 1){ //2 1's were rolled, both player score, and their total score is = 0
			scores[0] = 0;
			scoreTracker(0,scores,true, false);	
		} // ends else if (both dice roll 1)	
		//only one 1 was rolled, turnScore is set to 0, and playerTurn is over.
		else{
			scoreTracker(0,scores,true, false);
		}// ends else
	}// playerTurn ends
	
	/*
	 * Will act as the rolling the die
	 * the roll method will be called with no arguments,
	 * and return 2 random numbers between 1-6.
	 */
	public static int[] roll(){
		int[] dieValues = new int[2]; 
		Random rand = new Random();
		dieValues[0] = rand.nextInt(6) + 1;
		dieValues[1] = rand.nextInt(6) + 1;
		return(dieValues); // change this to two random numbers when you look up random numbers.
	}
	
	/*
	 * this will keep track of the scores then determine who's turn is next.
	 */
	public static void scoreTracker(int turnScore, int[] scores, boolean humanTurn, boolean humanTurnNext){
		if (humanTurn == true){//it came from humans turn (update their score)
			scores[0] = scores[0] + turnScore;
			if (humanTurnNext == true){ //next turn is human turn
				playerTurn(turnScore,scores);
			}
			else{ //next turn is computer's
				computerTurn(0,scores);
			}
		}
		else{ //it came from computer's turn (update its score)
			scores[1] = scores[1] + turnScore;
			if (humanTurnNext == false){ //its now the computers turn
				computerTurn(turnScore,scores);
			}
			else{ //its now the player's turn
				playerTurn(0,scores);
			}
		}
		
	} // ends scoreTracker
	
	/* This class controls the computer's turn in this game
	 * The computer will opt to roll again if its score is not above or equal to 30
	 */
	private static void computerTurn(int turnScore, int[] scores) {	
		if (turnScore >= 30){ //if it gets enough points in the turn, it passes his turn
			scoreTracker(turnScore, scores, false, true);
		}
		// if the computer's turn does have more than 29 points roll the die and finish its turn
		int[] dieVals = roll(); //gets the values of the rolls
		turnScore = turnScore + dieVals[0] + dieVals[1]; //updates the turn score
		if (scores[1] + turnScore >= 100){
			System.out.println("The computer has beaten you.");
			System.exit(10);
		}
		//lets the user know whats happening
		System.out.println("The computer has rolled " + dieVals[0] + " " + dieVals[1] + " and has");
		System.out.println("Computer's total score of: " + scores[1] + "\n Computer's turn score of: " + turnScore);
		if (dieVals[0] != 1 && dieVals[1] != 1){ //no 1's were rolled, continue as normal.
			computerTurn(turnScore,scores);
		}
		//the user's turn is over, and their total score is reset to 0. (2 ones were rolled)
		else if (dieVals[0] == 1 && dieVals[1] == 1){
			scores[1] = 0; //reset the computer's total score to 0
			scoreTracker(0,scores,false, true); //end the computer's turn 
		} // ends else if (both dice roll 1)	
		//only one 1 was rolled, turnScore is set to 0, and computer's turn is over.
		else{
			scoreTracker(0,scores,false, true); //end the computer's turn with 0 turnScore
		}
	}
	
	/*
	 * Will be used to ask the user if they would like to pass their turn or not,
	 * The code will then be sent to the corresponding function.
	 */
	public static void playerOptions(int[] dieVals, int turnScore, int[] scores){
		Scanner reader = new Scanner(System.in); //following few lines show user info, and ask for input
		System.out.println("Your total score is: " + scores[0] + "\nYour turn's score is: " + turnScore);
		System.out.print("Would you like to roll again? (Y/N) \n");
		String playerChoice = reader.nextLine(); 
		reader.close();
		if (playerChoice.equalsIgnoreCase("Y")){ //Player does not want to pass
			playerTurn(turnScore,scores);
		}
		else if (playerChoice.equalsIgnoreCase("N")){ //Player does want to pass
			scoreTracker(turnScore,scores,true,false);
		}
		else{ //incorrect input, so re-asked
			System.out.print("That wasnt a valid option");
			playerOptions(dieVals,turnScore,scores);
		}//close else
	}//close playerOptions
	
}// class ends

