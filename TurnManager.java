import java.lang.Math;
import java.util.ArrayList;

/**
 * Manages turns.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class TurnManager extends GameElement
{
    private Character player, opponent;
    private String playerName, opponentName;
    private int damage = 0;
    
    // create empty options lists
    private ArrayList<TurnOption> playerOptions = new ArrayList<TurnOption>();
    private ArrayList<TurnOption> opponentOptions = new ArrayList<TurnOption>();
    
    // create turn options to be added and removed from options lists accordingly
    private TurnOption act = new Act();
    private TurnOption move = new Move();
    private TurnOption endTurn = new TurnOption(); //turnOption.execute() returns contTurn = false, contGame = true by default
    private TurnOption forfeit = new Forfeit();
    
    // CONSTRUCTOR
    
    public TurnManager(Character p, Character o)
    {
        player = p;
        opponent = o;
        playerName = p.getName();
        opponentName = o.getName();
    }
    
    // EXECUTE PLAYER'S TURN (continues until contTurn = false)
    
    public boolean playerTurn()
    {
        System.out.println("It's " + playerName + "'s turn.\n");
        int originalPos = player.getPosition();
        int timesMoved = 0;
        player.setAmtMoved(0);
        boolean contTurn = true;
        boolean contGame = true;
        playerOptions.add(move);
        playerOptions.add(act);
        playerOptions.add(endTurn);
        playerOptions.add(forfeit);
        
        while(contTurn == true && opponent.getHP() > 0)
        {
            player.displayDistances(opponent);
            int num = 0;
            for(int i = 0; i < playerOptions.size(); i++)
            {
                num = i + 1;
                System.out.println("\t" + num + ") " + playerOptions.get(i));
            }
            System.out.println();
            int choiceNum = chooseFrom(playerOptions.size()) - 1;
            TurnOption choice = playerOptions.get(choiceNum);
            boolean[] vals = choice.execute(player, opponent);
            contTurn = vals[0];
            contGame = vals[1];
            
            if(choice == act)
            {
                playerOptions.remove(choice);
            }
            else if(choice == move)
            {
                timesMoved++;
                if(player.getAmtMoved() >= player.getSpeed() || timesMoved == 2)
                {
                    playerOptions.remove(choice);
                }
            }
            
            if(playerOptions.size() == 2)
            {
                contTurn = false;
            }
        }
        playerOptions.clear();
        
        return contGame;
    }
    
    // EXECUTE'S OPPONENT'S TURN
    
    public void opponentTurn()
    {
        System.out.println("It's " + opponentName + "'s turn.\n");
        int choice = opponent.chooseRandomlyFrom(2);
        switch(choice)
        {
            case 1:
                opponent.moveRandom(opponent.getSpeed());
                break;
            case 2:
                opponent.chooseRandomAction(player);
                break;
            default:
                System.out.println(opponentName + "'s choice was out of bounds!\n");
                break;
        }
    }
}
