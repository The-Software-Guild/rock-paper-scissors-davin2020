package com.mthree.davin.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

//TODO 

// read user input x2 and error/type checking > add try/catch when parsing input values - number of rounds, play again, and chosen item
// REQS should user input int to choose rock/paper etc?

// check REQS re overall winner - coudl igore ties in terms of overall winner or deduct one point for a tie? >  Please indicate that there is no overall winner. > done

// tidy code eg var/func names & conventions & docs - playerUser playerComputer > wip > done
// review flowchart and reqs, before uploading to GH

// DONE
// change to do-while loop, until user says No > done
// sort our vars for each round score re user/computer/tie etc - ok now
// sort out GH Classroom and commit/push > tested ok

/**
 * Class to play console based game of Rock Paper Scissors
 * @author Davin
 */
public class RockPaperScissors {
//    int x = 0;
    //ISSUE static vars are meant to be constant and named like USER_SCORE
    static int userScore = 0;
    static int computerScore = 0;
    static int tieScore = 0;
    
//    public static int getScore() {
//        return 32;
//    }
//    public int getX() {
//        return x;
//    }
    
    public static void main(String[] args) {
        //vars dont need to be stsatic if declared inside main it seems? need update score method or to pass vars around as params? or keep as static CONSTATNS but change their value?
        
        Scanner lineScanner = new Scanner(System.in);
        
        Boolean playAgain = false;
        System.out.println("Welcomem to a console-based version of Rock Paper Scissors!");
        do {
            //reset to zero in case this is a new set of rounds
            userScore = 0;
            computerScore = 0;
            tieScore = 0;
            
            System.out.println("How many rounds would you like to play?");
            int numberOfRounds = 0;
            //read in number of rounds
            try {
                String input = lineScanner.nextLine();
                numberOfRounds  = Integer.parseInt(input);
            } catch(NumberFormatException ex) {
                System.out.println("Input could not be read as a number");
            }
            
            String[] availableOptions = {"rock", "paper", "scissors"};
            if (numberOfRounds >= 1 && numberOfRounds <= 10 ) {
                System.out.println("acceptable amount of rounds");

                for (int currentRound = 1; currentRound <= numberOfRounds; currentRound ++) {
                    System.out.println("\nWelcome to round: " + currentRound);
                    
                    System.out.println("Please enter your choice - either 1 for Rock, 2 for Paper or 3 for Scissors:");
                    //read user input, as int, then minus 1 for zero based arrays
                    //stretch goal - read user input as string and compare against randomly picked item
                    //String userInput = "rock";
                    //userInput is really the int they enter, passed into the availableOptions
//                    stringOperand1 = myScanner.nextLine();
                    String userChoice = "";
                    boolean isValidIndexNumber = false;
                    do {
                        try {
                            String userInput = lineScanner.nextLine();
                            int userInputNumber  = Integer.parseInt(userInput);
                            //System.out.println("line 81 : " );
                            if (userInputNumber >=1 && userInputNumber <= 3) {
                                //need to minus 1 for zero based indexing
                                userChoice = availableOptions[(userInputNumber - 1)];
                                isValidIndexNumber = true;
                                System.out.println("User chose : " + userChoice);
                            }    
                            else {
                                //System.out.println("Please enter a number between 1 and 3 only");
                                throw new NumberFormatException();
                            }
                        }
                        catch(NumberFormatException ex) {
                            System.out.println("Please enter a number between 1 and 3 only");
                        }
                    } while (!isValidIndexNumber);
                    
                    //Random rGen = new Random();
                    // nextInt(int range): Returns a random int value within the range: $ 0 <= value < range $
                    //using array length allows u extend functionaltiy to Rock Paper Scissors Lizard Spock at a later date
                    int randomIndex = new Random().nextInt(availableOptions.length);
                    String computerInput = availableOptions[randomIndex];
                    System.out.println("Computer chose: " + computerInput);
                    String result = determineCurrentWinner(userChoice, computerInput);
                    System.out.println("Winner for this round is: " + result);
                } //end of looping through all rounds

                //how to get scores if not static?
                System.out.println("\nOverall Score for All Rounds -");
                System.out.println("User Score: " + userScore);
                System.out.println("Computer Score: " + computerScore);
                System.out.println("Tie Score: " + tieScore);
                String overallWinner = determineOverallWinner();
                System.out.println("Overall Winner - " + overallWinner + "\n"); 
                
                //ask about playing again here
                System.out.println("Do you want to play again? Enter Y/Yes/1 for Yes or any other key/value for No");
                try {
                    String input = lineScanner.nextLine();
                    Boolean  res = input.trim().toLowerCase().equals("true");
                    System.out.println("RES " + res);
                    Boolean res2 = Boolean.parseBoolean(input);
                    System.out.println("RESULT parse boolean " + res2);

                    if (input.trim().toUpperCase().equals("Y") | input.trim().toUpperCase().equals("YES")) {
                        System.out.println("Y its true line 133");
                        playAgain = true;
                    }
    //                playAgain  = Boolean.parseBoolean(input);
                    //this throws excetion so never gets to set playAgain=true !? this never evaluates to true, usure why?
                    else if (Integer.parseInt(input) == 1 | res2 ) {
                    //else if (Integer.parseInt(input) == 1 | input.trim().toUpperCase().equals("TRUE") ) {
                        System.out.println("bool its true line 138");
                        //put try catch here?
                        playAgain = true;
                    }
                    else {
                        System.out.println("else clause line 144");
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
    * @return String indicating the winner or if nobody won
    */
    //this mighthh not work, if its a draw?
    //REQ =  declare the overall winner based on who won more rounds.
    //ISSUE = if User and Tie OR Computer and Tie are equal, then who has actually won overall?
    
    // check REQS re overall winner - coudl igore ties in terms of overall winner or deduct one point for a tie? >  Please indicate that there is no overall winner. 
    private static String determineOverallWinner() {
        String overallWinner = "";
        if  (userScore > computerScore && userScore > tieScore) {
            overallWinner = "User";
        }
        else if  (computerScore > userScore && computerScore > tieScore) {
            overallWinner = "Computer";
        }
        else if  (userScore == computerScore ) {
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
    * IDEA better to use separate function to increment score, so function only has one responsibility
    * 
    * @param userInput, String indicating if User chose Rock, Paper or Scissors
    * @param computerInput, String indicating if Computer chose Rock, Paper or Scissors
    * @return String indicating if the User or Computer won, of if it was a Tie
    */
    private static String determineCurrentWinner(String userInput, String computerInput) {
        String winner = "";
        if (doesXBeatY(userInput, computerInput)){
             winner = "User Wins";
             //could put score in separate method n pass in param re user
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
