
/**
 * Big hammer.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Warhammer extends Action
{
    public Warhammer(Character u)
    {
        super(u);
        setName("Warhammer");
        setDescription("deals 1d8");
        setRange(5);//5
    }
    
    @Override
    public int rollDamage()
    {
        return d8();
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " swings the Warhammer.");
    }
}
