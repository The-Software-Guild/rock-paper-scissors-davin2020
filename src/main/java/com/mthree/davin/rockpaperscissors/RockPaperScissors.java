/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.davin.rockpaperscissors;

import java.util.Random;

//TODO read user input x2
//check reqs re overall winner
//tidy code
//sort our vars for each round score re user/computer/tie etc

/**
 *
 * @author River
 */
public class RockPaperScissors {
    static int userScore = 0;
    static int computerScore = 0;
    static int tieScore = 0;
    
    public static void main(String[] args) {
        System.out.println("Welcomem to Rock Paper Scissors");
        System.out.println("How many rounds would you like to play?");
        //read in number of rounds
        
        int numberOfRounds = 2;
        startGame(numberOfRounds);

        //assumes x rounds have been played by now
        //TODO ask user if they want to play again - how to call main from here? put it in another function!
        //BUG change to do-while (userChoice == yes)
        System.out.println("Do you wnat to play again?");
        String userChoice = "Yes";
        if (userChoice == "No") {
            System.out.println("Thanks for Playing, Bye");
        }
        else if (userChoice == "Yes") {
            //call new function ie main
            System.out.println("How many rounds would you like to play?");
            //BUG need to reset vars re uses, pc, tie score etc - prob better w getters/setters etc
            userScore = 0;
            computerScore = 0;
            tieScore = 0;
            //read in number of rounds
        
            int numberOfRounds2 = 3;
            startGame(numberOfRounds2);
        }
    }
    
    public static void startGame(int numberOfRounds) {
        //int userScore = 0;
        //int computerScore = 0;
        //int tieScore = 0;
        //check stuff or force to lowercase
        String optionsArray[] = {"rock", "paper", "scissors"};
        if (numberOfRounds >=1 && numberOfRounds <=10 ) {
            System.out.println("acceptable amount of rounds");
            
            for (int index=1; index <= numberOfRounds; index++) {
                System.out.println("\nWelcome to round: " + index);
                System.out.println("Please enter your choice: ");
                //read user input, as int, then minus 1 for zero based arrays
                //stretch goal - read user input as string and compare against randomly picked item
                String userInput = "rock";

                Random rGen = new Random();
                // nextInt(int range): Returns a random int value within the range: $ 0 <= value < range $
                int rInt = rGen.nextInt(3);
                System.out.println("Computer picks random int: " + rInt);
                String computerInput = optionsArray[rInt];
                System.out.println("Computer picks random item: " + computerInput);
                String result = checkWinner(userInput, computerInput);
                System.out.println("TEMP RESULT: " + result);
            }
            
            System.out.println("\nSCORE USER: " + userScore);
            System.out.println("SCORE COMPUTER: " + computerScore);
            System.out.println("SCORE TIE: " + tieScore);
            String overallWinner = checkOverallWinner();
            System.out.println("\nOVERALL WINNNER : " + overallWinner); 
        }
        else {
            System.out.println("Error, number of rounds must be between 1 and 10. Exiting");
        }  
    }
    
    //this mighthh not work, if its a draw?
    //REQ =  declare the overall winner based on who won more rounds.
    //ISSUE = if User and Tie OR Computer and Tie are equal, then who has actually won overall?
    public static String checkOverallWinner() {
        String overallWinner = "";
        if  (userScore > computerScore && userScore > tieScore) {
            overallWinner = "User";
        }
        else if  (computerScore > userScore && computerScore > tieScore) {
            overallWinner = "Computer";
        }
        else if  (tieScore > userScore && tieScore > computerScore) {
            overallWinner = "DRAW";
        }
        else if  (userScore == computerScore ) {
            overallWinner = "TIE between user and computer";
        }
        else if  (tieScore == computerScore ) {
            overallWinner = "TIE between Tie and computer";
        }
        else if  (tieScore == userScore ) {
            overallWinner = "TIE between Tie and User";
        }
        return overallWinner;
    }
    
    public static String checkWinner(String userInput, String computerInput) {
        String winner = "";
        if (doesXBeatY(userInput, computerInput)){
             winner = "User Wins";
             userScore ++;
        }
        else if (doesXBeatY(computerInput, userInput)) {
             winner = "Computer Wins";
             computerScore++;
        }
        else {
            winner = "Its a tie";
            tieScore++;
        }
        return winner;
    }
    
    public static boolean doesXBeatY(String x, String y) {
        if (x == "paper" && y == "rock") {
		return true;
	}
	else if (x == "rock" && y == "scissors") {
		return true;
	}
	else if (x == "scissors" && y == "paper") {
		return true;
	}
	else { 
		return false;
	}
    }
    
}
