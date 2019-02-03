import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

/**
 * A character
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */

public abstract class Character extends GameElement
{
    // general info
    private String name;
    private String fullName;
    private int maxHP;
    private int ac;
    private int baseSpeed;
    private int speed;
    
    // modifiers
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    
    // pronouns
    private String they = "they";
    private String they2 = "They";
    private String them = "them";
    private String them2 = "Them";
    private String their = "their";
    private String their2 = "Their";
    private String theirs = "theirs";
    private String theirs2 = "Theirs";
    private String themself = "themself";
    private String themself2 = "Themself";
    
    // values that change
    private int hp;
    private int position;
    private int amtMoved = 0;
    private boolean mounted = false;
    
    // attacks & actions
    private ArrayList<Action> actionList = new ArrayList<Action>();
    
    Random random = new Random();
    Scanner keyboard = new Scanner(System.in);
    
    // TOSTRING METHOD
    
    @Override
    public String toString()
    {
        return "\t" + name.toUpperCase() + ": " + hp + "/" + maxHP + " HP";
    }
    
    // EDIT ACTIONLIST
    
    public void addAction(Action action)
    {
        actionList.add(action);
    }
    
    public void removeAction(Action action)
    {
        actionList.remove(action);
    }
    
    // DISPLAY ACTION MENU FOR PLAYER TO PICK FROM
    
    public void chooseAction(Character target)
    {
        int num = 0;
        for(int i = 0; i < actionList.size(); i++)
        {
            num = i + 1;
            System.out.println("\t" + num + ") " + actionList.get(i));
        }
        System.out.println();
        int choiceNum = chooseFrom(actionList.size()) - 1;
        Action choice = actionList.get(choiceNum);
        choice.use(target);
    }
    
    // PICK RANDOM ACTION FROM LIST OF CHOICES
    
    public void chooseRandomAction(Character target)
    {
        //System.out.println(name + " is picking a random action.");
        int choiceNum = random.nextInt(actionList.size());
        Action choice = actionList.get(choiceNum);
        choice.use(target);
    }
    
    // INTRO NARRATION - default text, overwritten by individual characters
    
    public void intro(String opponentName)
    {
        System.out.println(name + " stands across from " + opponentName + " in a large circular arena. This is the Bureau of Balance's\n"
                            + "newest training facility, and today the Director has asked them to break it in with a sparring match.\n");
    }
    
    public void challenge(String playerName)
    {
        System.out.println(name + " challenges " + playerName + " to a battle!\n");
    }
    
    // END OF BATTLE NARRATION - default text, overwritten by individual characters
    
    public void forfeit(String opponentName)
    {
        System.out.println(name + " throws in the towel.\n");
    }
    
    // ROLL INITIATIVE
    
    public int rollInitiative()
    {
        int initiative = d20() + dexterity;
        System.out.print(name + " rolls " + initiative + ". ");
        return initiative;
    }
    
    // DISPLAY DISTANCE BTWN PLAYER & OPPONENT, BTWN PLAYER & WALL
    
    public void displayDistances(Character opponent)
    {
        int distance = opponent.getPosition() - position;
        String direction = distance < 0 ? "left" : "right";
        System.out.println(distance == 0 ? opponent.getName() + " is right on top of " + name + "!"
                            : opponent.getName() + " is " + Math.abs(distance) + " ft to the " + direction + ".");
        distance = position < 0 ? -50 - position : 50 - position;
        direction = distance < 0 ? "left" : "right";
        String wall = position < 0 ? "left" : "right";
        System.out.println(distance == 0 ? name + " is up against the " + wall + " wall.\n"
                            : "The nearest wall is " + Math.abs(distance) + " ft to the " + direction + ".\n");
    }
    
    // LET PLAYER CHOOSE HOW FAR TO MOVE
    
    public void move()
    {
        int maxDistance = speed - amtMoved;
        System.out.print("\tHow far? (" + maxDistance + "/" + speed + " ft remaining)\n\n>>> ");
        int distance = keyboard.nextInt();
        amtMoved += Math.abs(distance);
        System.out.println();
        
        if(Math.abs(distance) > maxDistance)
        {
            System.out.println(name + " can't move farther than " + their + " speed!");
            if(distance < 0)
            {
                distance = 0 - maxDistance;
            }
            else
            {
                distance = maxDistance;
            }
        }
        
        executeMove(distance);
    }
    
    // RANDOMLY CHOOSE HOW FAR OPPONENT SHOULD MOVE
    
    public void moveRandom(int maxDistance)
    {
        int distance = random.nextInt(maxDistance);
        int posOrNeg = random.nextInt(2);
        if(posOrNeg == 0)
        {
            distance = 0 - distance;
        }
        
        executeMove(distance);
    }
    
    // EXECUTE MOVE BASED ON DISTANCE PASSED IN
    
    public void executeMove(int distance)
    {
        position += distance;
        
        if(Math.abs(position) > 50)
        {
            System.out.println(name + " can't leave the arena!");
            if(distance > 0)
            {
                distance -= (50 - distance);
                position = 50;
                System.out.println(name + " moves up against the arena's right wall.\n");
            }
            else
            {
                distance -= (-50 - distance);
                position = -50;
                System.out.println(name + " moves up against the arena's left wall.\n");
            }
        }
        else
        {
            if(distance > 0)
            {
                System.out.println(name + " moves " + distance + " ft to the right.\n");
            }
            else if(distance < 0)
            {
                System.out.println(name + " moves " + Math.abs(distance) + " ft to the left.\n");
            }
            else
            {
                System.out.println(name + " stays where " + they + " is.\n");
            }
        }
    }
    
    // CHANGE HP (DAMAGE/HEAL)
    
    public void takeDamage(int damage)
    {
        System.out.println(name + " takes " + damage + " damage.\n");
        hp -= damage;
        if(hp <= 0)
        {
            System.out.println(name + " is unable to battle.\n");
        }
        else
        {
            System.out.println(this + "\n"); // show status
        }
    }
    
    public void heal(int healAmt)
    {
        int maxHealAmt = maxHP - hp;
        if(hp == maxHP)
        {
            System.out.println(name + " is already at full health.\n");
        }
        else
        {
            if(healAmt > maxHealAmt)
            {
                healAmt = maxHealAmt;
            }
            System.out.println(name + " regains " + healAmt + " hit points.\n");
            hp += healAmt;
            System.out.println(this + "\n"); // show status
        }
    }
    
    // GETTERS
    
    public String getName()
    {
        return name;
    }
    
    public String getFullName()
    {
        return fullName;
    }
    
    public int getHP()
    {
        return hp;
    }
    
    public int getAC()
    {
        return ac;
    }
    
    public int getBaseSpeed()
    {
        return baseSpeed;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public int getStrength()
    {
        return strength;
    }
    
    public int getDexterity()
    {
        return dexterity;
    }
    
    public int getConstitution()
    {
        return constitution;
    }
    
    public int getIntelligence()
    {
        return intelligence;
    }
    
    public int getWisdom()
    {
        return wisdom;
    }
    
    public int getCharisma()
    {
        return charisma;
    }
    
    public int getPosition()
    {
        return position;
    }
    
    public int getAmtMoved()
    {
        return amtMoved;
    }
    
    public boolean getMounted()
    {
        return mounted;
    }
    
    public String getPronouns(int p)
    {
        switch(p)
        {
            case 1:
                return they;
            case 12:
                return they2;
            case 2:
                return them;
            case 22:
                return them2;
            case 3:
                return their;
            case 32:
                return their2;
            case 4:
                return theirs;
            case 42:
                return theirs2;
            case 5:
                return themself;
            case 52:
                return themself2;
            default:
                return they;
        }
    }
    
    // SETTERS
    
    public void setName(String p)
    {
        name = p;
    }
    
    public void setFullName(String p)
    {
        fullName = p;
    }
    
    public void setHP(int p)
    {
        hp = p;
        maxHP = p;
    }
    
    public void setAC(int p)
    {
        ac = p;
    }
    
    public void setBaseSpeed(int p)
    {
        baseSpeed = p;
        speed = p;
    }
    
    public void setSpeed(int p)
    {
        speed = p;
    }
    
    public void setStrength(int p)
    {
        strength = p;
    }
    
    public void setDexterity(int p)
    {
        dexterity = p;
    }
    
    public void setConstitution(int p)
    {
        constitution = p;
    }
    
    public void setIntelligence(int p)
    {
        intelligence = p;
    }
    
    public void setWisdom(int p)
    {
        wisdom = p;
    }
    
    public void setCharisma(int p)
    {
        charisma = p;
    }
    
    public void setPosition(int p)
    {
        position = p;
    }
    
    public void setAmtMoved(int p)
    {
        amtMoved = p;
    }
    
    public void setMounted(boolean p)
    {
        mounted = p;
    }
    
    public void setPronouns(char p)
    {
        switch(p)
        {
            case 'h':
                they = "he";
                they2 = "He";
                them = "him";
                them2 = "Him";
                their = "his";
                their2 = "His";
                theirs = "his";
                theirs2 = "His";
                themself = "himself";
                themself2 = "Himself";
                break;
            case 's':
                they = "she";
                they2 = "She";
                them = "her";
                them2 = "Her";
                their = "her";
                their2 = "Her";
                theirs = "hers";
                theirs2 = "Hers";
                themself = "herself";
                themself2 = "Herself";
                break;
            default:
                break;
        }
    }
}
