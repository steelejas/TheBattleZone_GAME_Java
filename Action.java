
/**
 * Any action besides moving, mainly attacks.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public abstract class Action extends GameElement
{
    // set by superclass constructor
    private Character user;
    
    // set by subclass constructor
    private String name = "Unnamed Action";
    private String description = "N/A";
    private int range = 3;
    private boolean limited = false;
    private int usesLeft = 1;
    private int mod = 0;
    private boolean hasSave = false;
    private int saveType = 0; // 1=con, 2=dex, 3=wisdom
    private boolean saveCancels = false; //does a successful save cancel all effects?
    private int dc = 0;
    private int dcMod = 0; //1=str, 2=dex, 3=con, 4=int, 5=wis, 6=cha
    private int profBonus = 0;
    private boolean damaging = true;
    private boolean healing = false;
    
    // CONSTRUCTOR
    
    public Action(Character u)
    {
        user = u;
    }
    
    // TOSTRING METHOD
    
    @Override
    public String toString()
    {
        String limitStr = (limited == false) ? " / unl." : " / " + usesLeft + " left";
        String modStr = (mod == 0) ? "" : " +" + mod;
        String profStr = (profBonus == 0) ? "" : " +" + profBonus;
        String rangeStr = (range == 0) ? " / self" : " / " + range + " ft range";
        return name + " [" + description + modStr + profStr + rangeStr + limitStr + "]";
    }
    
    // EXECUTE THE CHOSEN ACTION
    
    public void use(Character target)
    {
        // narrate character using attack
        useMessage(user, target);
        
        if(checkInRange(target) == true)
        {
            boolean saved = false;
            if(hasSave == true)
            {
                saved = checkSavingThrow(target);
                if(saved == true && saveCancels == true)
                {
                    // narrate opponent's reaction to the attack failing
                    showFailedRxn(user.getName(), target.getName());
                    return; // exit method; action is cancelled by successful save
                }
            }
            
            if(healing == true)
            {
                int healAmt = rollHealing() + mod + profBonus;
                user.heal(healAmt);
            }
            
            if(damaging == true)
            {
                boolean hit = checkHit(target);
                if(hit == true)
                {
                    int damage = rollDamage() + mod + profBonus;
                    if(saved == true)
                    {
                        target.takeDamage(damage/2);
                    }
                    else
                    {
                        target.takeDamage(damage);
                    }
                    
                    // execute additional effects that take effect on a successful hit ie. knockback
                    executeHitEffect(user, target);
                    
                    if(target.getMounted() == true)
                    {
                        target.setMounted(false);
                        target.setSpeed(target.getBaseSpeed());
                        System.out.println("'Peace out, dudes,' Garyl says. He vanishes in a puff of smoke.\n\n"
                                            + target.getName() + " is no longer mounted. " + target.getPronouns(32) + " speed is now " + target.getSpeed() + ".\n");
                    }
                }
            }
            
            // execute additional effects that happen no matter what ie. speed change
            executeGeneralEffect(user, target);
            
            // narrate opponent's reaction to the attack succeeding
            showRxn(user.getName(), target.getName());
            
            if(limited == true)
            {
                usesLeft--;
                if(usesLeft == 0)
                {
                    user.removeAction(this);
                }
            }
        }
    }
    
    // RETURN DISTANCE BTWN USER & TARGET
    
    public int checkDistance(Character target)
    {
        // return distance btwn user and target
        return Math.abs(user.getPosition() - target.getPosition());
    }
    
    // CHECK IF ACTION IS WITHIN RANGE
    
    public boolean checkInRange(Character target)
    {
        int distance = checkDistance(target);
        if(range == 0)
        {
            // action affects self only
            return true;
        }
        else if(distance > range)
        {
            System.out.println("The attack is out of range!\n");
            return false;
        }
        else
        {
            // attack is within range
            return true;
        }
    }
    
    // CHECK IF TARGET SAVES (successful save can change damage amt or cancel action entirely)
    
    public boolean checkSavingThrow(Character target)
    {
        boolean saved = false;
        int goal = dc + profBonus;
        switch(dcMod)
        {
            case 1:
                goal += user.getStrength();
                break;
            case 2:
                goal += user.getDexterity();
                break;
            case 3:
                goal += user.getConstitution();
                break;
            case 4:
                goal += user.getIntelligence();
                break;
            case 5:
                goal += user.getWisdom();
                break;
            case 6:
                goal += user.getCharisma();
                break;
            default:
                break;
        }
        
        int roll = d20();
        int total = 0;
        if(roll == 1)
        {
            System.out.print(target.getName() + " rolls a 1. Critical failure! ");
        }
        else if(roll == 20)
        {
            System.out.print(target.getName() + " rolls a 20. Critical success! ");
            total = goal + 1;
        }
        else
        {
            int modVal = 0;
            switch(saveType)
            {
                case 1:
                    modVal = user.getStrength();
                    break;
                case 2:
                    modVal = user.getDexterity();
                    break;
                case 3:
                    modVal = user.getConstitution();
                    break;
                case 4:
                    modVal = user.getIntelligence();
                    break;
                case 5:
                    modVal = user.getWisdom();
                    break;
                case 6:
                    modVal = user.getCharisma();
                    break;
                default:
                    break;
            }
            total = roll + modVal;
            System.out.print(target.getName() + " rolls " + roll + " + " + modVal + " = " + total + " vs " + goal + " DC. ");
        }
        
        if(total > goal)
        {
            System.out.println("Saving throw successful.");
            saved = true;
        }
        else
        {
            System.out.println("Saving throw unsuccessful.");
        }
        
        return saved;
    }
    
    // CHECK IF DAMAGING ATTACK HITS
    
    public boolean checkHit(Character target)
    {
        int goal = target.getAC();
        int roll = d20();
        int total = 0;
        if(roll == 1)
        {
            System.out.print(user.getName() + " rolls a 1. Critical failure! ");
        }
        else if(roll == 20)
        {
            System.out.print(user.getName() + " rolls a 20. Critical success! ");
            total = goal + 1;
        }
        else
        {
            int modVal = user.getDexterity();
            total = roll + modVal;
            System.out.print(user.getName() + " rolls " + roll + " + " + modVal + " = " + total + " vs " + goal + " AC. ");
            
        }
        
        if(total < goal)
            {
                System.out.println("The attack misses.\n");
                return false;
            }
            else
            {
                System.out.println("The attack hits!");
                return true;
            }
    }
    
    // DEFAULT METHODS TO BE OVERWRITTEN
    
    public int rollDamage()
    {
        // roll dice to determine damage
        return 0;
    }
    
    public int rollHealing()
    {
        // roll dice to determine heal amount
        return 0;
    }
    
    public void useMessage(Character user, Character target)
    {
        // narrate character using action
        System.out.println(user.getName() + " uses " + name + ".");
    }
    
    public void executeGeneralEffect(Character user, Character target)
    {
        // overwritten only by actions that have additional effects that take effect no matter what ie. speed change
    }
    
    public void executeHitEffect(Character user, Character target)
    {
        // overwritten only by actions that have additional effects that take effect on a successful hit ie. knockback
    }
    
    public void showRxn(String userName, String targetName)
    {
        // overwritten only by actions that trigger cutscenes when the action succeeds
    }
    
    public void showFailedRxn(String userName, String targetName)
    {
        // overwritten only by actions that trigger cutscenes when the action fails
    }
    
    // GETTERS
    
    public Character getUser()
    {
        return user;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getRange()
    {
        return range;
    }

    public int getMod()
    {
        return mod;
    }
    
    public boolean getLimited()
    {
        return limited;
    }
    
    public int getUsesLeft()
    {
        return usesLeft;
    }
    
    public int getDC()
    {
        return dc;
    }
    
    public int getDCMod()
    {
        return dcMod;
    }
    
    public boolean getHasSave()
    {
        return hasSave;
    }
    
    public boolean getSaveCancels()
    {
        return saveCancels;
    }
    
    public int getSaveType()
    {
        return saveType;
    }
    
    public int getProfBonus()
    {
        return profBonus;
    }
    
    public boolean getDamaging()
    {
        return damaging;
    }
    
    public boolean getHealing()
    {
        return healing;
    }
    
    // SETTERS
    
    public void setUser(Character p)
    {
        user = p;
    }
    
    public void setName(String p)
    {
        name = p;
    }
    
    public void setDescription(String p)
    {
        description = p;
    }
    
    public void setRange(int p)
    {
        range = p;
    }

    public void setMod(int p)
    {
        mod = p;
    }
    
    public void setLimited(boolean p)
    {
        limited = p;
    }
    
    public void setUsesLeft(int p)
    {
        usesLeft = p;
    }
    
    public void setHasSave(boolean p)
    {
        hasSave = p;
    }
    
    public void setSaveType(int p)
    {
        saveType = p;
    }
    
    public void setSaveCancels(boolean p)
    {
        saveCancels = p;
    }
    
    public void setDC(int p)
    {
        dc = p;
    }
    
    public void setDCMod(int p)
    {
        dcMod = p;
    }
    
    public void setProfBonus(int p)
    {
        profBonus = p;
    }
    
    public void setDamaging(boolean p)
    {
        damaging = p;
    }
    
    public void setHealing(boolean p)
    {
        healing = p;
    }
}
