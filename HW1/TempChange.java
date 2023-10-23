public class TempChange {
   public static void main(String[] args) {
    if (args.length != 9){
        System.out.println("Please enter the correct inputs") ;
        return;
    }
    String city1 = args[0]; 
    Double citi1TempX = Double.parseDouble(args[1]); 
    Double citi1TempY = Double.parseDouble(args[2]); 
    String city2 = args[3]; 
    Double citi2TempX = Double.parseDouble(args[4]); 
    Double citi2TempY = Double.parseDouble(args[5]); 
    String city3 = args[6]; 
    Double citi3TempX = Double.parseDouble(args[7]); 
    Double citi3TempY = Double.parseDouble(args[8]); 

    Double citi1TempDelta = Math.abs(citi1TempX - citi1TempY);
    Double citi2TempDelta = Math.abs(citi2TempX - citi2TempY);
    Double citi3TempDelta = Math.abs(citi3TempX - citi3TempY);
    String outputCity;
    Double outputDelta;
    if (citi1TempDelta > citi2TempDelta){
        if (citi3TempDelta > citi1TempDelta){
            outputCity = city3;
            outputDelta = citi3TempDelta;
        }
        else{
            outputCity = city1;
            outputDelta = citi1TempDelta;
        }
    }
    else{
        if (citi3TempDelta > citi2TempDelta){
            outputCity = city3;
            outputDelta = citi3TempDelta;
        }
        else{
            outputCity = city2;
            outputDelta = citi2TempDelta;
        }
    }

    System.out.printf(outputCity + " had the biggest change: %.2f", outputDelta) ;
   }
}