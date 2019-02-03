
/**
 * Spell that blasts target with magic missiles.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class MagicMissile extends Action
{
    public MagicMissile(Character u)
    {
        super(u);
        setName("Magic Missile");
        setDescription("deals 1d4+1 x3 darts");
        setRange(120);
        setLimited(true);
        setUsesLeft(5);
    }
    
    @Override
    public int rollDamage()
    {
        return 3 * (d4() + 1);
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " launches three magic missiles towards " + target.getName() + ".");
    }
}
