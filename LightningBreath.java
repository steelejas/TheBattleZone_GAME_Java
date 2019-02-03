
/**
 * Blue dragonborn's ability to breathe lightning.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class LightningBreath extends Action
{
    public LightningBreath(Character u)
    {
        super(u);
        setName("Lightning Breath");
        setDescription("deals 4d6 (halved on save)");
        setRange(30);//30
        setHasSave(true);
        setSaveType(2); //dex
        setDC(8);
        setDCMod(3); //con
    }
    
    @Override
    public int rollDamage()
    {
        return 4 * d6();
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " breathes lightning.");
    }
}
