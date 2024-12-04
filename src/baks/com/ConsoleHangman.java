package baks.com;

import java.util.Scanner;

public class ConsoleHangman {
    public void startGame() {
        Dictionary dictionary = new WordDictionary();
        Session session = new Session(dictionary, 10);
        Scanner in = new Scanner(System.in);
        System.out.println("Game started! Write your first letter or 'exit' to give up:");
        while (true) {
            GuessResult guessResult = tryGuess(session, in.nextLine());

            printStatus(guessResult);

            if (guessResult instanceof GuessResult.Win || guessResult instanceof GuessResult.Defeat) {
                break;
            }


        }
    }

    private GuessResult tryGuess(Session session, String input){
        if(input.length() == 1){
            return session.guess(Character.toLowerCase(input.charAt(0)));
        }else if(input.equalsIgnoreCase("exit")){
            return session.giveUp();
        } else {
            return new GuessResult.FailedGuess(session.getUserAnswer(), session.getUsedAttempts(), session.getMaxAttempts(), "Invalid input. Please enter a single letter or 'exit' to give up.");
        }
    }

    private void printStatus(GuessResult guessResult){
        System.out.println(guessResult.message());
        System.out.println("Attempts: " + guessResult.attempt() + "/" + guessResult.maxAttempts());
        System.out.println("State: " + formattedState(guessResult.state()));

        System.out.println();
    }

    private String formattedState(char[] state){
        StringBuilder sb = new StringBuilder();
        for (char c : state) {
            if(c == 0){
                sb.append("_ ");
            }else{
                sb.append(Character.toUpperCase(c)).append(" ");
            }
        }

        return sb.toString();
    }


}
