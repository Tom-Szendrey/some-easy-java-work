import java.util.Random;
import java.util.Scanner;

//one of the versions of pig ive made,
//Due to it being a remake of an assignment i didnt use many comments (opps)

public class pig {
	
	public static void main(String[] args){
		//first one human score, 2nd element is the computer score.
		int[] scores = new int[2];
		int nextTurn = 0; //while this is 0, its the humans turn. While this is 1, its the computers.
		while(scores[0] < 100 && scores[1] < 100){
			if (nextTurn == 0){
				nextTurn = playerTurn(scores,0);
			}
			else{
				nextTurn = computerTurn(scores);
				System.out.println("-------------------------------");
			}
		}
	}//ends main
	
	public static int[] roll(){
		int[] diceValues = new int[2];
	    Random rand= new Random ();
	    int temp= rand.nextInt(6) +1;
	    diceValues[0]= temp;
	    Random rand1= new Random ();
	    int temp2= rand1.nextInt(6) +1;
	    diceValues[1]=temp2;
	    return diceValues;
	}//ends roll
	
	public static int playerTurn(int[] scores, int playerTurnScore){
		int[] dieVals = roll();
		playerTurnScore += dieVals[0] + dieVals[1];
		System.out.println("You have Rolled " + dieVals[0] + " and " + dieVals[1]);
	    if(dieVals[0] == 1 && dieVals[1] == 1){
	    	scores[0] = 0;
	    	return 1;
	    }// end if both values are 1
	    else if (dieVals[0] == 1 || dieVals[1]== 1){
	    	playerTurnScore = 0;
	    	return 1;
	    }// end if one value is 1
	    else if (dieVals[0]== dieVals[1]){
	    	System.out.println("You Have Rolled Doubles And Must Roll Again"); //testing
	    	playerTurn(scores, playerTurnScore);
	    }// roll again if numbers are the same
	    else{
	    	printOptions(scores, playerTurnScore);
	    }
		return 1;
	}// ends playerTurn

    @SuppressWarnings("resource")
	public static void printOptions(int[] bothScores , int currentScore){
    	System.out.println("Player Score = " + bothScores[0]);
    	System.out.println("Computer Score = " + bothScores[1]);
    	System.out.println("Your Turn Score = " + currentScore);
    	System.out.println("Enter A Number ");
    	System.out.println("Press 1 To Roll Again ");
    	System.out.println("Press 2 To End Your Turn ");
		Scanner reader = new Scanner(System.in);
    	int playerChoice = reader.nextInt();
    	
    	if (playerChoice == 1){
    		playerTurn(bothScores, currentScore);
    	}
    	else if(playerChoice == 2){ 
    		scoreTracker(bothScores, currentScore, "Player");
    	}
    	else{
    		System.out.println("Not A Valid Option.");
    		printOptions(bothScores,currentScore);
    	}
    } // printOptions
    
    public static void scoreTracker(int[] bothScores, int currentScore, String whosTurn){
    	if(whosTurn == "Player"){
    		bothScores[0] += currentScore;
    		if(bothScores[0] >= 100){
    			System.out.println("Congraulations You Have Won");
    		}
        }
    	else{ //computer's turn
    		bothScores[1] += currentScore;
    		if(bothScores[1] >= 100){
    			System.out.println("Sorry the Computer Has Won Better Luck Next Time");
    		}
    	}
    } //end of scoreTracker
    
    public static int computerTurn(int[] scores){
    	System.out.println("-------------------------------");
    	int computerTurnScore = 0;
    	while(computerTurnScore < 20){
	    	int[] dieVals = roll();
			computerTurnScore += dieVals[0] + dieVals[1];
			System.out.println("Computer has Rolled " + dieVals[0] + " and " + dieVals[1]);
			if(dieVals[0] == 1 && dieVals[1] == 1){
		    	scores[1] = 0;
		    	return 0;	    	
		    }// end if both values are 1
		    else if (dieVals[0] == 1 || dieVals[1]== 1){
		    	return 0;
		    }// end if one value is 1
		    else{
		    	System.out.println("Computer Has Rolled Again"); //testing
		    }// rolls again if computerTurnScore is less than 30
    	}//ends while loop
    	//this is when you call scoreTracker, playerTurn(scores, 0);
    	scoreTracker(scores, computerTurnScore, "Computer");
    	return 0;
    }// end computer turn
}//ends class

