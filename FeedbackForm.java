import java.util.Scanner;
import java.io.*;

/**
 * Collects user feedback.
 *
 * @author Jasmine Steele
 * @version 5/12/18
 */
public class FeedbackForm
{
    private Scanner keyboard = new Scanner(System.in);
    private FileWriter fWriter;
    private PrintWriter feedbackFile;
    private String feedback, name;
    private Character player, opponent;
    
    public FeedbackForm(Character p, Character o) throws IOException
    {
        fWriter = new FileWriter("FEEDBACK.txt", true);
        feedbackFile = new PrintWriter(fWriter);
        player = p;
        opponent = o;
    }
    
    // GET USER FEEDBACK
    
    public void getFeedback()
    {
        System.out.print("Any feedback? Enter your comment below and it will be appended to 'FEEDBACK.txt' in this project folder,\n"
                            + "or enter '0' to skip.\n\n\t>>> ");
        feedback = keyboard.nextLine();
        if(!feedback.equals("0"))
        {
            System.out.print("\nEnter your name and the date to be saved along with your feedback.\n\n\t>>> ");
            name = keyboard.nextLine();
            feedbackFile.println(name);
            feedbackFile.println("[played as " + player.getName() +", fought " + opponent.getName() + "]");
            feedbackFile.println(feedback);
            feedbackFile.println("---------------------------------------------");
        }
        feedbackFile.close();
    }
}
