import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Program to implement RSVP speed reader using StdDraw library
 * 
 * @author Chris Murphy
 */

public class SpeedReader {

    public static void main(String[] args) {
    	if (args.length != 2){
            System.out.println("Please specify the file name and wpm") ;
            return;
        }
    	int wpm = Integer.parseInt(args[1]);
    	if (wpm <= 0) {
    		System.out.println("Please specify a positive wpm") ;
    		return;
        }
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);

        // this enables animation so that things don't appear jittery
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new java.awt.Font("COURIER", java.awt.Font.BOLD, 100));
        
        // this is the number to be displayed in the window
		String word = "";
		String redLetter = "";
        try (Scanner in = new Scanner(new File(args[0]))){
			while (in.hasNext()) {
				word = in.next();
				
				if (word.length() % 2 == 1) {   //odd
					redLetter = Character.toString(word.charAt(word.length()/2));
					draw(true,word,redLetter, wpm);
				}
				else {							//even
					redLetter = Character.toString(word.charAt(word.length()/2));
					draw(false,word,redLetter, wpm);
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error reading file");
		}
    }
    
    public static void draw(boolean odd, String word, String redLetter, int wpm) {
    	// this places the text in the center of the screen
        // however, it is not displayed until StdDraw.show() is called
    	StdDraw.setPenColor(StdDraw.BLACK);  
    	if (odd) {
    		StdDraw.text(50, 50, word);
    	}
    	else {
    		StdDraw.text(50-7.5/2, 50, word);
    	}
    	
        StdDraw.setPenColor(StdDraw.RED);        
        StdDraw.text(50, 50, redLetter);


        // this displays the text
        StdDraw.show();

        // this causes the program to wait for 500ms, which is 0.5 seconds
        int delay = 1000 / (wpm / 60);
        StdDraw.pause(delay);

        // this removes everything that is being displayed
        StdDraw.clear();
    }
    
}
