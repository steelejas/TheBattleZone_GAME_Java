
/**
 * Magnus Hyphen Terry "The Hammer" Burnsides, human fighter-rogue and folk hero.
 *
 * @author Jasmine Steele
 * @version 5/5/18
 */

public class Magnus extends Character
{
    private Action railsplitter = new Railsplitter(this);
    private Action chanceLance = new ChanceLance(this);
    private Action phantomFist = new PhantomFist(this);
    
    public Magnus()
    {
        setName("Magnus");
        setFullName("Magnus Burnsides, human fighter");
        setPronouns('h');
        setHP(131);
        setAC(20);
        setBaseSpeed(30);
        
        setStrength(8);
        setDexterity(2);
        setConstitution(7);
        setIntelligence(0);
        setWisdom(1);
        setCharisma(1);
        
        railsplitter.setMod(getStrength());
        chanceLance.setMod(getStrength());
        phantomFist.setMod(getStrength());
        
        railsplitter.setProfBonus(5);
        
        addAction(railsplitter);
        addAction(chanceLance);
        addAction(phantomFist);
        
    }
    
    @Override
    public void challenge(String playerName)
    {
        System.out.println("Magnus carefully places Steven's tank by the edge of the arena and then draws Railsplitter with a grin.");
        if(playerName.equals("Merle"))
        {
            System.out.println("'You ready, Merle?' he says.\n\n"
                                + "Merle chuckles nervously. 'Well, I don't know if READY is the right word, but hey, I'll do my best!'\n");
        }
        else if(playerName.equals("Taako"))
        {
            System.out.println("'You ready, Taako?' he says.\n\n"
                                + "Taako twirls the Umbra Staff in his hand. 'Hell yeah, dude, let's do it.'\n");
        }
        else if(playerName.equals("Carey"))
        {
            System.out.println("'You ready, lizard girl?' he says.\n\n"
                                + "'Still a proud dragonborn,' Carey says, unsheathing her daggers. She returns the grin. 'Let's dance, spaceman.'\n");
        }
        else
        {
            super.challenge(playerName);
        }
    }
    
    @Override
    public void forfeit(String opponentName)
    {
        System.out.println("Magnus stops running, chest heaving. He smiles weakly.\n"
                            + "'Sorry, " + opponentName + ", I think I'm done. I'm gonna go sit down. Good game!'\n");
    }
}
