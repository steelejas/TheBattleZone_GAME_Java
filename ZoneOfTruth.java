
/**
 * Spell that forces the target to tell the truth.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class ZoneOfTruth extends Action
{
    public ZoneOfTruth(Character u)
    {
        super(u);
        setName("Zone of Truth");
        setDescription("effect (negated on save)");
        setRange(60);//60
        setDamaging(false);
        setLimited(true);
        setUsesLeft(1);
        setHasSave(true);
        setSaveType(5); //wis
        setSaveCancels(true);
        setDC(0);
        setDCMod(5); //wis
    }
    
    @Override
    public void useMessage(Character user, Character target)
    {
        System.out.println(user.getName() + " casts ZONE OF TRUTH!");
    }
    
    // triggered on failed save (meaning the spell succeeded & target must tell the truth)
    
    @Override
    public void showRxn(String userName, String targetName)
    {
        System.out.println();
        if(targetName.equals("Magnus"))
        {
            System.out.println("Magnus laughs. 'What do you expect? I'm not keeping any secrets, Merle!'\n\n"
                                + "Merle shrugs. 'You can never be too sure these days.'\n");
        }
        else if(targetName.equals("Taako"))
        {
            System.out.println("'I NEVER KNEW THE DIFFERENCE BETWEEN MACAROONS AND MACARONS UNTIL ANGUS TOLD ME,' Taako blurts out.\n"
                                + "He clamps his hands over his mouth. 'I- I mean...' He points the Umbra Staff at Merle.\n"
                                +"'Hey, let's just fight, why don't we? Why complicate it, let's just damage each other!'\n");
        }
        else if(targetName.equals("Carey"))
        {
            System.out.println("'I'm not hiding anything, dude,' Carey says with a quizzical smile.\n\n"
                                + "'Yeah, that's what all you rogues say,' Merle says.\n");
        }
        else
        {
            System.out.println(targetName + " spills the beans.\n");
        }
    }
    
    // triggered on successful save (meaning the spell failed & target can lie)
    
    @Override
    public void showFailedRxn(String userName, String targetName)
    {
        System.out.println();
        if(targetName.equals("Magnus"))
        {
            System.out.println("Magnus laughs. 'What do you expect? I'm not keeping any secrets, Merle!'\n\n"
                                + "Merle shrugs. 'You can never be too sure these days.'\n");
        }
        else if(targetName.equals("Taako"))
        {
            System.out.println("Taako swoons dramatically. 'Oh, you got me, Merle! I can't hide the truth from you any longer.\n"
                                + "Magnus and I aren't your real parents. You're adopted. This is a Zone of Truth, Merle, I can't lie.'\n\n"
                                + "Merle sighs. 'Guess I wasted that one, huh?'\n");
        }
        else if(targetName.equals("Carey"))
        {
            System.out.println("'I'm not hiding anything, dude,' Carey says with a quizzical smile.\n\n"
                                + "'Yeah, that's what all you rogues say,' Merle says.\n");
        }
        else
        {
            System.out.println(targetName + " tells a blatant lie and there's nothing " + userName + " can do about it.\n");
        }
    }
}
