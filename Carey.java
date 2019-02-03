
/**
 * Carey Fangbattle, proud dragonborn rogue.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */

public class Carey extends Character
{
    private Action dagger = new Dagger(this);
    private Action lightning = new LightningBreath(this);
    private Action punch = new Punch(this);
    
    /**
     * Constructor for objects of class Carey
     */
    public Carey()
    {
        setName("Carey");
        setFullName("Carey Fangbattle, dragonborn rogue");
        setPronouns('s');
        setHP(70);
        setAC(14);
        setBaseSpeed(40);
        
        setStrength(2);
        setDexterity(8);
        setConstitution(0);
        setIntelligence(4);
        setWisdom(1);
        setCharisma(4);
        
        dagger.setMod(getDexterity());
        punch.setMod(getStrength());
        
        dagger.setProfBonus(5);
        lightning.setProfBonus(5);
        
        addAction(dagger);
        addAction(lightning);
        addAction(punch);
        
    }
    
    @Override
    public void challenge(String playerName)
    {
        System.out.println("Carey stretches her arms above her head and then lowers into a fighting stance, daggers at the ready.");
        if(playerName.equals("Magnus"))
        {
            System.out.println("'You ready, spaceman?' she says with a grin.\n\n"
                                + "Magnus draws Railsplitter. 'I was born ready.'\n");
        }
        else if(playerName.equals("Taako"))
        {
            System.out.println("'You ready, Taako? I won't go easy!' she says with a grin.\n\n"
                                + "Taako twirls the Umbra Staff in his hand. 'Hell yeah, dude, let's do it.'\n");
        }
        else if(playerName.equals("Merle"))
        {
            System.out.println("'You ready, old man? I won't go easy!' she says with a grin.\n\n"
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
        System.out.println("Carey stops running and steadies herself on her knees, catching her breath."
                            + "\n'Sorry, " + opponentName + ", I'm beat. It was a good fight! I'll getcha next time.'\n");
    }
}
