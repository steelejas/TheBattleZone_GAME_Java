
/**
 * Forfeits the battle, ends the game
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Forfeit extends TurnOption
{
    @Override
    public String toString()
    {
        return "Forfeit";
    }
    
    @Override
    public boolean[] execute(Character user, Character target)
    {
        user.forfeit(target.getName());
        boolean[] vals = {false, false};
        return vals;
    }
}
