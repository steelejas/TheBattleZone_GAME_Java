
/**
 * Earl Merle Hightower Highchurch, dwarven cleric of Pan.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class Merle extends Character
{
    private Action warhammer = new Warhammer(this);
    private Action zoneOfTruth = new ZoneOfTruth(this);
    private Action prayerOfHealing = new PrayerOfHealing(this);
    private Action punch = new Punch(this);
    
    public Merle()
    {
        setName("Merle");
        setFullName("Merle Highchurch, dwarven cleric");
        setPronouns('h');
        setHP(71);
        setAC(18);
        setBaseSpeed(25);
        
        setStrength(6);
        setDexterity(1);
        setConstitution(3);
        setIntelligence(0);
        setWisdom(3);
        setCharisma(1);
        
        zoneOfTruth.setMod(getWisdom());
        prayerOfHealing.setMod(getWisdom());
        warhammer.setMod(getStrength());
        punch.setMod(getStrength());
        
        zoneOfTruth.setProfBonus(5);
        prayerOfHealing.setProfBonus(5);
        
        addAction(warhammer);
        addAction(zoneOfTruth);
        addAction(prayerOfHealing);
        addAction(punch);
        
    }
    
    @Override
    public void challenge(String playerName)
    {
        if(playerName.equals("Magnus"))
        {
            System.out.println("Magnus carefully places Steven's tank by the edge of the arena and then draws Railsplitter with a grin.\n" 
                                +"'You ready, Merle?' he says.\n\n"
                                + "Merle chuckles nervously. 'Well, I don't know if READY is the right word, but hey, I'll do my best!'\n");
        }
        else if(playerName.equals("Taako"))
        {
            System.out.println("'God, I'm sparring with you?' Taako sighs. 'Well, this'll be a quick one, I guess.'\n\n"
                                + "Merle wags a wooden finger. 'Don't be too sure about that! I've been training too, y'know,' he says with a smug smile.\n\n"
                                + "Taako raises an eyebrow. 'Yeah, we'll see...'\n");
        }
        else if(playerName.equals("Carey"))
        {
            System.out.println("Carey stretches her arms above her head and then lowers into a fighting stance, daggers at the ready.\n"
                                + "'You ready, old man? I won't go easy!' she says with a grin.\n\n"
                                + "Merle smiles. 'Course not! I wouldn't have it any other way.'\n");
        }
        else
        {
            super.challenge(playerName);
        }
    }
    
    @Override
    public void forfeit(String opponentName)
    {
        System.out.println("Merle sits down on the ground and wipes his brow."
                            + "\n'Whew! I gotta stop here, " + opponentName + ". I'm gettin' too old for this.'\n");
    }
}
