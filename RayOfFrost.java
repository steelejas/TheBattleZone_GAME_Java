
/**
 * Spell that blasts opponents with a ray of frost.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class RayOfFrost extends Action
{
    public RayOfFrost(Character u)
    {
        super(u);
        setName("Ray of Frost");
        setDescription("deals 3d8");
        setRange(60);
    }
    
    @Override
    public int rollDamage()
    {
        return 3 * d8();
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " blasts " + target.getName() + " with a glittering ray of frost.");
    }
}
