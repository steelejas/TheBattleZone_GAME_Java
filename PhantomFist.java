
/**
 * Glove that knocks back the target.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class PhantomFist extends Action
{
    public PhantomFist(Character u)
    {
        super(u);
        setName("Phantom Fist");
        setDescription("deals 1d3+1 (knocks opponent back 2d4 ft)");
        setRange(3);//5
    }
    
    @Override
    public int rollDamage()
    {
        return d3() + 1;
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " throws a punch with Phantom Fist.");
    }
    
    @Override
    public void executeHitEffect(Character user, Character target)
    {
        System.out.println("A spectral force sends " + target.getName() + " flying backward.");
        int distance = 2 * d4();
        int vector = user.getPosition() >= target.getPosition() ? -distance :  distance;
        // if target is to the left of user OR on top of user, target flies left (assuming right-handedness by default); else, target flies right
        target.executeMove(vector);
    }
}
