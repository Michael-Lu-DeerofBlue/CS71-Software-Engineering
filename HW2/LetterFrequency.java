import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LetterFrequency {
	public static void main(String[] args) {
    	if (args.length != 1){
            System.out.println("No input file") ;
            return;
        }
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, 26);
        StdDraw.setYscale(0, 1);

        // this enables animation so that things don't appear jittery
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new java.awt.Font("COURIER", java.awt.Font.BOLD, 20));
        
        // this is the number to be displayed in the window
		String word = "";
		char letter;
		int index = 0;
		double[] frequencyArray = new double[26];
        try (Scanner in = new Scanner(new File(args[0]))){
			while (in.hasNext()) {
				word = in.next().toLowerCase();
				for (int i = 0; i < word.length(); i++) {
					letter = word.charAt(i);
					index = letter - 'a';
					if (index >= 0 && index <= 25) {
						frequencyArray[index]++;
					}					
				}			
			}
			double mostCommon = 0;
			for (int i = 0; i < 26; i++) {
				if (i == 0) {
					mostCommon = frequencyArray[i];
				}
				else if (frequencyArray[i] > mostCommon) {
					mostCommon = frequencyArray[i];
				}
			}
			for (int i = 0; i < 26; i++) {
				frequencyArray[i] = frequencyArray[i]/ mostCommon;
				if ((i%2) == 0) {
					StdDraw.setPenColor(StdDraw.CYAN);
				}
				else {
					StdDraw.setPenColor(StdDraw.BLUE);
				}
				StdDraw.filledRectangle(i + 0.5, 0.5 * frequencyArray[i], 0.5, 0.5 * frequencyArray[i]);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(i + 0.5, 0.08, Character.toString('a'+i));
			}
			StdDraw.show();
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error reading file");
		}
    }
	
}
