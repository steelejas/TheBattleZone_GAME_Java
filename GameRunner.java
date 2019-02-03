import java.util.Scanner;
import java.io.*;

/**
 * Title of application: The Battle Zone
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class GameRunner
{    
    public static void main(String[] args) throws IOException
    {
        // initialize utilities that do not require characters (Scanner, CharacterSetup)
        Scanner keyboard = new Scanner(System.in);
        CharacterSetup setup = new CharacterSetup();
        
        // display instructions
        System.out.println("\t.:: THE BATTLE ZONE ::.\n\n"
                            + "Welcome to the Battle Zone, a turn-based fighting game based on popular D&D podcast 'The Adventure Zone'!\n"
                            + "To make a choice, type the number of your choice and hit 'Enter'.\n"
                            + "Each turn, you can perform one action and move up to your speed.\n"
                            + "You may also break up your move -- ie. if your speed is 30, you may move 10 ft, act, then move 20 ft.\n"
                            + "When moving, enter a negative number to move left and a positive number to move right.\n");
        
        // pick characters
        Character player = setup.pickPlayer();
        Character opponent = setup.pickOpponent();
        
        // get character info
        String playerName = player.getName();
        String playerFullName = player.getFullName();
        String opponentName = opponent.getName();
        String opponentFullName = opponent.getFullName();
        
        // position characters
        player.setPosition(-5);
        opponent.setPosition(5);
        
        // initialize utilities that require characters (TurnManager, FeedbackForm)
        TurnManager turn = new TurnManager(player, opponent);
        FeedbackForm feedbackForm = new FeedbackForm(player, opponent);
        
        // announce start of battle
        System.out.println();
        player.intro(opponentName);
        opponent.challenge(playerName);
        
        // roll initiative
        System.out.println("Rolling initiative...");
        int playerInit = player.rollInitiative();
        int opponentInit = opponent.rollInitiative();
        System.out.println("\n" + (playerInit >= opponentInit ? playerName : opponentName)
                            + " goes first.\n");
        
        // initialize vars to manage game loop
        int round = 0;
        boolean cont = true;
        
        // game loop
        while(cont == true)
        {
            round++;
            System.out.println("--------------------------------------------- ROUND " + round + " ---------------------------------------------\n");
            setup.displayStatus(); // show everyone's hp
            if(playerInit >= opponentInit) // player goes first
            {
                cont = turn.playerTurn();
                if(cont == true)
                {
                    if(opponent.getHP() > 0)
                    {
                        turn.opponentTurn();
                    }
                    else
                    {
                        opponent.forfeit(playerName);
                        cont = false;
                    }
                }
            }
            else // opponent goes first
            {
                turn.opponentTurn();
                if(player.getHP() > 0)
                {
                    cont = turn.playerTurn();
                }
                else
                {
                    player.forfeit(opponentName);
                    cont = false;
                }
            }
        }
        
        System.out.println("-------------------------------------------- GAME OVER --------------------------------------------\n");
        
        // get user feedback
        feedbackForm.getFeedback();
        
        // end game
        System.out.println("\nThank you for playing! Hit any key and press 'Enter' to exit.\n");
        keyboard.next();
        System.exit(0);
    }
}
