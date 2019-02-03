
/**
 * Taako "from TV" Taaco, elven transmutation wizard (with no transmutation spells, oops).
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Taako extends Character
{
    private Action rayOfFrost = new RayOfFrost(this);
    private Action magicMissile = new MagicMissile(this);
    private Action phantomSteed = new PhantomSteed(this);
    private Action punch = new Punch(this);
    
    public Taako()
    {
        setName("Taako");
        setFullName("Taako Taaco, elven wizard");
        setPronouns('h');
        setHP(49);
        setAC(12);
        setBaseSpeed(30);
        
        setStrength(0);
        setDexterity(1);
        setConstitution(7);
        setIntelligence(8);
        setWisdom(5);
        setCharisma(0);
        
        rayOfFrost.setMod(getStrength());
        magicMissile.setMod(getStrength());
        punch.setMod(getStrength());
        
        magicMissile.setProfBonus(5);
        
        addAction(rayOfFrost);
        addAction(magicMissile);
        addAction(phantomSteed);
        addAction(punch);
        
    }
    
    @Override
    public void challenge(String playerName)
    {
        if(playerName.equals("Magnus"))
        {
            System.out.println("Magnus carefully places Steven's tank by the edge of the arena and then draws Railsplitter with a grin.\n"
                                +"'You ready, Taako?' he says.\n\n"
                                + "Taako twirls the Umbra Staff in his hand. 'Hell yeah, dude, let's do it.'\n");
        }
        else if(playerName.equals("Merle"))
        {
            System.out.println("'God, I'm sparring with you?' Taako sighs. 'Well, this'll be a quick one, I guess.'\n\n"
                                + "Merle wags a wooden finger. 'Don't be too sure about that! I've been training too, y'know,' he says with a smug smile.\n\n"
                                + "Taako raises an eyebrow. 'Yeah, we'll see...'\n");
        }
        else if(playerName.equals("Carey"))
        {
            System.out.println("Carey stretches her arms above her head and then lowers into a fighting stance, daggers at the ready.\n"
                                + "'You ready, Taako? I won't go easy!' she says with a grin.\n\n"
                                + "Taako twirls the Umbra Staff in his hand. 'Hell yeah, dude, let's do it.'\n");
        }
        else
        {
            super.challenge(playerName);
        }
    }
    
    @Override
    public void forfeit(String opponentName)
    {
        System.out.println("Taako collapses in a heap on the ground. 'Oh, the pain!' he cries. 'Oh, the humanity! You've beaten me so thoroughly,\n"
                            + opponentName + ", that I have no choice but to return to my room and go back to sleep. What a shame...!'\n");
    }
}
