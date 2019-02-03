
/**
 * Recallable lance that looks like a clock hand.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class ChanceLance extends Action
{
    public ChanceLance(Character u)
    {
        super(u);
        setName("Chance Lance");
        setDescription("deals 1d6+8");
        setRange(20);
    }
    
    @Override
    public int rollDamage()
    {
        return d6() + 8;
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " throws the Chance Lance.");
    }
}
