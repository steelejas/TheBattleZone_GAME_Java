
/**
 * Spell that heals the user through Pan's blessing.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class PrayerOfHealing extends Action
{
    public PrayerOfHealing(Character u)
    {
        super(u);
        setName("Prayer of Healing");
        setDescription("heals 3d8");
        setRange(0);
        setMod(5); //wis
        setLimited(true);
        setUsesLeft(2);
        setDamaging(false);
        setHealing(true);
    }
    
    @Override
    public int rollHealing()
    {
        return 3 * d8();
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " asks Pan for his blessing.");
    }
}
