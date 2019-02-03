import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Sets up characters.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class CharacterSetup
{
    Scanner keyboard = new Scanner(System.in);
    Random random = new Random();
    
    // create empty character list
    private ArrayList<Character> characterList = new ArrayList<Character>();
    
    // create characters
    private Character magnus = new Magnus();
    private Character taako = new Taako();
    private Character merle = new Merle();
    private Character carey = new Carey();
    
    // create player/opponent reference vars w/ no initialization value
    private Character player;
    private Character opponent;
    
    // CONSTRUCTOR
    
    public CharacterSetup()
    {
        characterList.add(magnus);
        characterList.add(taako);
        characterList.add(merle);
        characterList.add(carey);
    }
    
    // LET PLAYER PICK PLAYER CHARACTER
    
    public Character pickPlayer()
    {
        System.out.println("\tChoose your fighter!\n");
        for(int i = 0; i < characterList.size(); i++)
        {
            Character chara = characterList.get(i);
            System.out.println("\t" + (i + 1) + ") " + chara.getFullName());
        }
        System.out.print("\n>>> ");
        player = characterList.get(keyboard.nextInt() - 1);
        characterList.remove(player);
        return player;
    }
    
    // RANDOMLY PICK OPPONENT FROM REMAINING CHARACTERS
    
    public Character pickOpponent()
    {
        int oppIndex = random.nextInt(characterList.size());
        opponent = characterList.get(oppIndex);
        return opponent;
    }
    
    // DISPLAY EVERYONE'S HP
    
    public void displayStatus()
    {
        System.out.println(player + "\n" + opponent + "\n");
    }
}
