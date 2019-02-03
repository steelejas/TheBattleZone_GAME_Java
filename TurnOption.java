
/**
 * An option on the menu the player sees at the start of their turn, End Turn by default
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class TurnOption
{
    @Override
    public String toString()
    {
        return "End turn";
    }
    
    // ends turn by default, subclasses for other options override this
    public boolean[] execute(Character user, Character target)
    {
        boolean[] vals = {false, true}; //contTurn, contGame
        return vals;
    }
}
