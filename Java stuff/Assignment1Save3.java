import java.util.Random;
import java.util.Scanner;
/**
 * This program is used to play the game pig against 
 * 'AI' (very loose term). 
 * 
 *Start Date:2016/09/27 
 *Last Updated:2016/10/05
 *Tom Szendrey, 10187030, Assignment1, pig game
 */
public class Assignment1Save3 {
	//as with every other main method, this is used to start the game
	public static void main(String[] args){
		int[] scores = {0,0}; //total scores, where scores[0] is the humans score, and scores[1] is the computers score
		playerTurn(0,scores); //The user has first turn.
	}//end of main
	
	/*
	 * The playerTurn method will act as the player's turn
	 * This method will check if the user is allowed to roll again or if they have to, or if they have to skip their team
	 * If they have a choice to go again or not, the user input is done in another method.
	 * This method also updates the turn score for the player
	 */
	public static void playerTurn(int turnScore, int[] scores){
		int[] dieVals = roll(); //gets 2 values into a list using roll()
		System.out.println("You have rolled: " + dieVals[0] + " " + dieVals[1]);
		turnScore = turnScore + dieVals[0] + dieVals[1]; //update turnScore
		//check if the game is over
		if (scores[0] + turnScore >= 100){ 
			System.out.println("You have won! /nYour final score was: " + scores[0]);
			System.exit(10); 
		}//ends game over check
		//The user is forced to go again as they have rolled doubles (and not snake eyes)
		if (dieVals[0] == dieVals[1] && dieVals[0] != 1){
			System.out.println("Because you have rolled doubles you must roll again.");
			playerTurn(turnScore,scores);	
		}
		//The user has the choice the go again or not.
		if (dieVals[0] != 1 && dieVals[1] != 1){ //no ones were rolled, game continues normally 
			playerOptions(dieVals,turnScore,scores); //get the user's input to continue or pass
		}//ends if dieVals != 1
		//the user's turn is over, and their total score is reset to 0.
		else if (dieVals[0] == 1 && dieVals[1] == 1){ //2 1's were rolled, both player score, and their total score is = 0
			scores[0] = 0;
			computerTurn(0,scores);
		} // ends else if (both dice roll 1)	
		//only one 1 was rolled, turnScore is set to 0, and playerTurn is over.
		else{
			computerTurn(0,scores);
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
	 * this will keep track of the scores 
	 * Im aware that I could do this function in other methods using 1 line
	 * But this helps me read everything easier.
	 */
	public static int scoreUpdate(int turnScore, int score){
			score = score + turnScore;
			return(score);
	} // ends scoreUpdate
	
	/* This class controls the computer's turn in this game
	 * The computer will opt to roll again if its score is not above or equal to 30
	 */
	public static void computerTurn(int turnScore, int[] scores) {	
		// if the computer's turn does have more than 29 points roll the die and finish its turn
		int[] dieVals = roll(); //gets the values of the rolls
		turnScore = turnScore + dieVals[0] + dieVals[1]; //updates the turn score
		if (scores[1] + turnScore >= 100){
			System.out.println("The computer has beaten you with a score of: " + scores[1]);
			System.exit(10);
		}
		//lets the user know whats happening
		System.out.println("The computer has rolled " + dieVals[0] + " " + dieVals[1] + " and has");
		System.out.println("Computer's total score of: " + scores[1] + "\n Computer's turn score of: " + turnScore);
		if (dieVals[0] == dieVals[1] && dieVals[0] != 1){ //the computer HAS to go again, it rolled doubles.
			computerTurn(turnScore,scores);
		}
		if (dieVals[0] != 1 && dieVals[1] != 1){ //no 1's were rolled, continue as normal.
			computerTurn(turnScore,scores);
		}
		//the user's turn is over, and their total score is reset to 0. (2 ones were rolled)
		else if (dieVals[0] == 1 && dieVals[1] == 1){
			scores[1] = 0; //reset the computer's total score to 0
			playerTurn(0,scores); //end the computer's turn 
		} // ends else if (both dice roll 1)	
		//only one 1 was rolled, turnScore is set to 0, and computer's turn is over.
		else{
			playerTurn(0,scores);//end the computer's turn with 0 turnScore
		}
		//if it gets enough points in the turn, it passes his turn		
		if (turnScore >= 25){
			scores[1] = scoreUpdate(turnScore, scores[1]); //update comp's score
			playerTurn(0,scores); //give it to player turn
		}
		//If the computer doesnt have enough points, it rolls again.
		else{ 
			computerTurn(turnScore,scores);
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
		String playerChoice = reader.next(); //gets the user input
		if (playerChoice.equalsIgnoreCase("Y")){ //Player wants to roll again
			playerTurn(turnScore,scores);
		}
		else if (playerChoice.equalsIgnoreCase("N")){ //Player wants to pass
			scores[0] = scoreUpdate(turnScore,scores[0]);
			computerTurn(0,scores);
		}
		else{ //incorrect input, so re-asked
			System.out.print("That wasnt a valid option");
			playerOptions(dieVals,turnScore,scores);
		}//close else
	}//close playerOptions
}// class ends

