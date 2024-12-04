package baks.com;

import java.util.Collection;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int usedAttempts;

    public Session(Dictionary dictionary, int maxAttempts) {
        this.answer = dictionary.randomWord();
        this.userAnswer = new char[answer.length()];
        this.maxAttempts = maxAttempts;
        usedAttempts = 0;
    }

    public GuessResult guess(char guess){
        boolean alreadyExists = false;
        boolean found = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                if(userAnswer[i] == guess){
                    alreadyExists = true;
                }
                userAnswer[i] = guess;
                found = true;
            }
        }



        if(found){
            if(String.valueOf(userAnswer).equals(answer)){
                return new GuessResult.Win(userAnswer, usedAttempts, maxAttempts, "Congratulations! You've won!");
            }else{
                if(alreadyExists){
                    return new GuessResult.SuccessfulGuess(userAnswer, usedAttempts, maxAttempts, "This letter is already in the word! Nothing changed");
                }else{
                    return new GuessResult.SuccessfulGuess(userAnswer, usedAttempts, maxAttempts, "You've found the letter!");
                }
            }
        }else{
            usedAttempts++;
            if(usedAttempts >= maxAttempts){
                return new GuessResult.Defeat(userAnswer, usedAttempts, maxAttempts, "You've lost! The correct answer was: " + answer);
            }else{
                return new GuessResult.FailedGuess(userAnswer, usedAttempts, maxAttempts, "Nope, the letter was not found! Try again. Attempts left: " + (maxAttempts - usedAttempts));
            }
        }




    }
    public GuessResult giveUp(){
        return new GuessResult.Defeat(userAnswer, usedAttempts, maxAttempts, "You gave up! The correct answer was: " + answer);
    }


    public String getAnswer() {
        return answer;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getUsedAttempts() {
        return usedAttempts;
    }
}

