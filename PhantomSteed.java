
/**
 * Spell that summons a large quasi-real spectral horse-like creature.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class PhantomSteed extends Action
{
    public PhantomSteed(Character u)
    {
        super(u);
        setName("Phantom Steed");
        setDescription("speed inc. to 100 ft (ends upon taking damage)");
        setRange(0);
        setDamaging(false);
        setLimited(true);
        setUsesLeft(1);
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        if(user.getName().equals("Taako"))
        {
            System.out.println("Taako casts Phantom Steed. In a gust of wind, Garyl the spectral unicorn rises majestically from\n"
                                + "the earth beneath his feet, rainbow mane billowing.\n\n" 
                                + "'Yeah dude, what's happenin'?' Garyl drawls. 'Sparring match? Nice.'\n");
        }
        else
        {
            System.out.println(user.getName() + " casts Phantom Steed. A large, quasi-real spectral horse-like creature appears\n"
                                + "beside "+ user.getPronouns(2) +", which " + user.getPronouns(1) + " mounts.");
        }
    }
    
    @Override
    public void executeGeneralEffect(Character user, Character target)
    {
        System.out.println(user.getName() + " is now mounted. " + user.getPronouns(32) + " speed is now 100!\n");
        user.setMounted(true);
        user.setSpeed(100);
    }
}
