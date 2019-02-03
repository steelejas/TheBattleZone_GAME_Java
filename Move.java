
/**
 * Move to the left or right
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Move extends TurnOption
{
    @Override
    public String toString()
    {
        return "Move";
    }
    
    @Override
    public boolean[] execute(Character user, Character target)
    {
        user.move();
        boolean[] vals = {true, true};
        return vals;
    }
}
