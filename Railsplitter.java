
/**
 * Cool axe that chops down trees (and targets)!
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Railsplitter extends Action
{
    public Railsplitter(Character u)
    {
        super(u);
        setName("Railsplitter");
        setDescription("deals 1d8+5");
        setRange(5);//5
    }
    
    @Override
    public int rollDamage()
    {
        return d8() + 5;
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " swings Railsplitter.");
    }
}
