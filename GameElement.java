import java.util.Random;
import java.util.Scanner;

/**
 * Anything that needs to roll dice or choose from a list.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public abstract class GameElement
{
    private Random random = new Random();
    private Scanner keyboard = new Scanner(System.in);
    
    // PICK RANDOMLY FROM LIST OF CHOICES
    
    public int chooseRandomlyFrom(int numChoices)
    {
        //System.out.println("Picking randomly from " + numChoices + " choices...");
        return random.nextInt(numChoices) + 1;
    }
    
    // LET USER PICK FROM LIST OF CHOICES (makes sure choice # is valid)
    
    public int chooseFrom(int numChoices)
    {
        System.out.print(">>> ");
        int choice = keyboard.nextInt();
        System.out.println();
        while(choice <= 0 || choice > numChoices)
        {
            System.out.println("\tInvalid input. Please enter the number of your choice.\n");
            System.out.print(">>> ");
            choice = keyboard.nextInt();
            System.out.println();
        }
        return choice;
    }
    
    // ROLL DICE
    
    public int d2(){
        return random.nextInt(2) + 1;
    }
    
    public int d3(){
        return random.nextInt(3) + 1;
    }
    
    public int d4(){
        return random.nextInt(4) + 1;
    }
    
    public int d6(){
        return random.nextInt(6) + 1;
    }
    
    public int d8(){
        return random.nextInt(8) + 1;
    }
    
    public int d12(){
        return random.nextInt(12) + 1;
    }
    
    public int d20(){
        return random.nextInt(20) + 1;
    }
}
