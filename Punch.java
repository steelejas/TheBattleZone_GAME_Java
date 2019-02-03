
/**
 * Just a normal punch.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Punch extends Action
{
    public Punch(Character u)
    {
        super(u);
        setName("Punch");
        setDescription("deals 1d3");
    }
    
    @Override
    public int rollDamage()
    {
        return d3();
    }
   
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " throws a punch.");
    }
}
