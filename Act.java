
/**
 * Carry out an action
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Act extends TurnOption
{
    @Override
    public String toString()
    {
        return "Act";
    }
    
    @Override
    public boolean[] execute(Character user, Character target)
    {
        user.chooseAction(target);
        boolean[] vals = {true, true};
        return vals;
    }
}
