
/**
 * Dagger to throw at opponents.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Dagger extends Action
{
    public Dagger(Character u)
    {
        super(u);
        setName("Dagger");
        setDescription("deals 1d4");
        setRange(20);
    }
    
    @Override
    public int rollDamage()
    {
        return d4();
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " throws a dagger.");
    }
}
