import java.io.FileWriter;
import java.io.IOException;

public class Logger{
    public static void write(FileWriter writer, String message) {
        try {
            writer.write(message + "\n");
        }
        catch(IOException e){
            System.out.println("An error occurred while writing the log file.");
        }        
    }
}