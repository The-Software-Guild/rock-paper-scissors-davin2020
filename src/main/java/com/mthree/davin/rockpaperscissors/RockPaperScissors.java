package com.mthree.davin.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 * Class to play console based game of Rock Paper Scissors
 * @author Davin
 */
public class RockPaperScissors {
    //ISSUE static vars are meant to be constant and named like USER_SCORE - could have sep method for line scanner too?
    static int userScore;
    static int computerScore;
    static int tieScore;
    static String[] availableOptions = {"rock", "paper", "scissors"};
    
    public static void main(String[] args) {
        Scanner lineScanner = new Scanner(System.in);
        Boolean playAgain = false;
        System.out.println("Welcomem to a console-based version of Rock Paper Scissors!");
        
        RockPaperScissors myVar = new RockPaperScissors();
        //can acces method calls now eg myVar.method() once remote static too
        
        
        do {
            //Reset to zero in case this is a new round, as part of do/while loop
            userScore = 0;
            computerScore = 0;
            tieScore = 0;
            int numberOfRounds = 0;
            
            //Read user's first input about number of rounds
            boolean isValidInt = false;
            do {
                System.out.println("How many rounds would you like to play?");
                String input = lineScanner.nextLine();
                try {
                    if (input == null || input.isEmpty()) {
                        System.out.println("Oops, you didn't enter anything");
                    } else {
                        numberOfRounds  = Integer.parseInt(input);
                        isValidInt = true;
                    }
                } catch(NumberFormatException ex) {
                    System.out.println("Input could not be read as a number");
                }
            } while (!isValidInt);
            
            // Start playing x nuumber of rounds
            //avail options was here
            
            if (numberOfRounds >= 1 && numberOfRounds <= 10 ) {
                //call new method
                playGame(numberOfRounds);
                
                System.out.println("Do you want to play again? Enter Y/Yes/true/1 for Yes or any other key/value for No");
                try {
                    String input = lineScanner.nextLine();
//                    boolean temp = Boolean.parseBoolean(input);
//                    System.out.println("TEMP " + temp);
                    
                    if (input.trim().toUpperCase().equals("Y") | input.trim().toUpperCase().equals("YES")) {
                        playAgain = true;
                    }
                    //FYI parseBoolean condition needs to be evaluated first, since it doesn't thrown an exception but parseInt does
                    else if (Boolean.parseBoolean(input) || Integer.parseInt(input) == 1  ) {
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
                playAgain = false;
            } //finished playing a valid number of rounds 
            
            //stop playing game function - really ends here

        } while (playAgain);
        System.out.println("Thanks for Playing! Bye");
    }
    
    private static void playGame(int numberOfRounds) {
        Scanner lineScanner = new Scanner(System.in);
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

                //Show overall scores and overall winner
                System.out.println("\nOverall Score for All Rounds -");
                System.out.println("User Score: " + userScore);
                System.out.println("Computer Score: " + computerScore);
                System.out.println("Tie Score: " + tieScore);
                String overallWinner = determineOverallWinner();
                System.out.println("Overall Winner - " + overallWinner + "\n"); 
                //stop playing game function ends here?
                
        
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
        //Added extra condition in case Tie is greater than User or Computer
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
    * using a helper function doesUserInputBeatComputerInput(), 
    * and then increment the score for the current winner
    * IDEA better to use separate function to increment score  
    * and pass in param of which player, so this function only has one responsibility
    * 
    * @param userInput, String indicating if User chose Rock, Paper or Scissors
    * @param computerInput, String indicating if Computer chose Rock, Paper or Scissors
    * @return String indicating if the User or Computer won, of if it was a Tie
    */
    private static String determineCurrentWinner(String userInput, String computerInput) {
        String winner = "";
        if (doesUserInputBeatComputerInput(userInput, computerInput)){
             winner = "User Wins";
             userScore ++;
        }
        else if (doesUserInputBeatComputerInput(computerInput, userInput)) {
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
    * Determine the winner between 2 chosen items/inputs, based on - 
    *   Paper beats Rock, Scissors bests Paper and Rock beats Scissors
    * 
    * @return Boolean of whether first item beats the second item
    */
    private static boolean doesUserInputBeatComputerInput(String userInput, String computerInput) {
        if (userInput.equals("paper") && computerInput.equals("rock")) {
            return true;
	}
        else if (userInput.equals("scissors") && computerInput.equals("paper")) {
            return true;
	}
	else if (userInput.equals("rock") && computerInput.equals("scissors")) {
            return true;
	}
	else { 
            return false;
	}
    }

}