package com.mthree.davin.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 * Class to play console based game of Rock Paper Scissors
 * @author Davin
 */
public class RockPaperScissors {
    //ISSUE static vars are meant to be constant and named like USER_SCORE
    static int userScore;
    static int computerScore;
    static int tieScore;
    
    public static void main(String[] args) {
        Scanner lineScanner = new Scanner(System.in);
        Boolean playAgain = false;
        System.out.println("Welcomem to a console-based version of Rock Paper Scissors!");
        do {
            //Reset to zero in case this is a new round, as part of do/while loop
            userScore = 0;
            computerScore = 0;
            tieScore = 0;
            System.out.println("How many rounds would you like to play?");
            int numberOfRounds = 0;
            try {
                String input = lineScanner.nextLine();
                numberOfRounds  = Integer.parseInt(input);
            } catch(NumberFormatException ex) {
                System.out.println("Input could not be read as a number");
            }
            String[] availableOptions = {"rock", "paper", "scissors"};
            if (numberOfRounds >= 1 && numberOfRounds <= 10 ) {
                for (int currentRound = 1; currentRound <= numberOfRounds; currentRound ++) {
                    System.out.println("\nWelcome to round: " + currentRound);
                    System.out.println("Please enter your choice - either 1 for Rock, 2 for Paper or 3 for Scissors:");
                    //Process the user's input and seleect valid index from array of avilable options
                    String userChoice = "";
                    boolean isValidIndexNumber = false;
                    do {
                        try {
                            String userInput = lineScanner.nextLine();
                            int userInputNumber  = Integer.parseInt(userInput);
                            if (userInputNumber >=1 && userInputNumber <= 3) {
                                //Need to minus 1 for zero based array index
                                userChoice = availableOptions[(userInputNumber - 1)];
                                isValidIndexNumber = true;
                                System.out.println("User chose: " + userChoice);
                            }    
                            else {
                                throw new NumberFormatException();
                            }
                        }
                        catch(NumberFormatException ex) {
                            System.out.println("Please enter a number between 1 and 3 only");
                        }
                    } while (!isValidIndexNumber);
                    
                    //Randomly pick an int to represent the Computer's choice
                    int randomIndex = new Random().nextInt(availableOptions.length);
                    String computerChoice = availableOptions[randomIndex];
                    System.out.println("Computer chose: " + computerChoice);
                    String result = determineCurrentWinner(userChoice, computerChoice);
                    System.out.println("Winner for this round is: " + result);
                } //End of looping through all rounds

                //Show overall scores and overall winner - ISSUE how to get scores if not static vars?
                System.out.println("\nOverall Score for All Rounds -");
                System.out.println("User Score: " + userScore);
                System.out.println("Computer Score: " + computerScore);
                System.out.println("Tie Score: " + tieScore);
                String overallWinner = determineOverallWinner();
                System.out.println("Overall Winner - " + overallWinner + "\n"); 
                
                System.out.println("Do you want to play again? Enter Y/Yes/1 for Yes or any other key/value for No");
                try {
                    String input = lineScanner.nextLine();
                    if (input.trim().toUpperCase().equals("Y") | input.trim().toUpperCase().equals("YES")) {
                        playAgain = true;
                    }
                    //ISSUE wanted to allow user to enter 'true' but unable to get Boolean.parseBoolean(input) working as part of IF statement
                    else if (Integer.parseInt(input) == 1 ) {
                        playAgain = true;
                    }
                    else {
                        playAgain  = false;
                    }
                } catch(NumberFormatException ex) {
                    System.out.println("Input could not be understood as 'Yes Play Again', so assuming No");
                    playAgain  = false;
                }
            }
            else {
                System.out.println("Something went wrong, the number of rounds must be between 1 and 10. Bye");
            } //finished playing a valid number of rounds 

        } while (playAgain);
        System.out.println("Thanks for Playing! Bye");
    }
    
    /**
    * Determine who is the overall winner, based on the score of User, Computer or Tie
    * 
    * @return String indicating the winner or if nobody won overall eg User and Tie have equal scores
    */
    private static String determineOverallWinner() {
        String overallWinner = "";
        if  (userScore > computerScore && userScore > tieScore) {
            overallWinner = "User";
        }
        else if  (computerScore > userScore && computerScore > tieScore) {
            overallWinner = "Computer";
        }
        //Added extra condition in case Tie is greater than User|Computer
        else if  (userScore == computerScore && userScore  > tieScore ) {
            overallWinner = "It's a tie betwen the User and the Computer";
        }
        else if  (tieScore >= computerScore || tieScore >= userScore ) {
            overallWinner = "Nobody won overall";
        }
        return overallWinner;
    }
    
    /**
    * Determine the winner for the current round between the User and Computer, 
    *   using a helper function doesXBeatY(), and then increment the score for the current winner
    * IDEA better to use separate function to increment score and pass in param of which player, 
    *   so this function only has one responsibility
    * 
    * @param userInput, String indicating if User chose Rock, Paper or Scissors
    * @param computerInput, String indicating if Computer chose Rock, Paper or Scissors
    * @return String indicating if the User or Computer won, of if it was a Tie
    */
    private static String determineCurrentWinner(String userInput, String computerInput) {
        String winner = "";
        if (doesXBeatY(userInput, computerInput)){
             winner = "User Wins";
             userScore ++;
        }
        else if (doesXBeatY(computerInput, userInput)) {
             winner = "Computer Wins";
             computerScore ++;
        }
        else {
            winner = "Its a tie";
            tieScore ++;
        }
        return winner;
    }
    
    /**
    * Determine the winner between 2 chosen items, based on - 
    *   Paper beats Rock, Scissors bests Paper and Rock beats Scissors
    * 
    * @return Boolean of whether first item beats the second item
    */
    private static boolean doesXBeatY(String x, String y) {
        if (x.equals("paper") && y.equals("rock")) {
            return true;
	}
        else if (x.equals("scissors") && y.equals("paper")) {
            return true;
	}
	else if (x.equals("rock") && y.equals("scissors")) {
            return true;
	}
	else { 
            return false;
	}
    }

}