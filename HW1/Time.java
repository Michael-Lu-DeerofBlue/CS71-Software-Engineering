public class Time {
   public static void main(String[] args) {
    if (args.length > 1){
        System.out.println("Please specify only one integer") ;
        return;
    }
    else if (args.length == 0){
        System.out.println("Please specify a positive integer") ;
        return;
    }
    int minutes = Integer.parseInt(args[0]); 
    if (minutes < 0){
        System.out.println("Please specify a positive integer") ;
        return;
    }
    int hours = minutes/60;
    int minutesLeft = minutes%60;
    if (hours == 0){
        if (minutesLeft == 1){
            System.out.println(minutesLeft + " minute") ;
        }
        else{
            System.out.println(minutesLeft + " minutes") ;
        }
    }
    else if (minutesLeft == 0){
        System.out.println(hours + " hours") ;
    }
    else{
        if (minutesLeft == 1){
            System.out.println(hours + " hours and " + minutesLeft + " minute") ;
        }
        else{
            System.out.println(hours + " hours and " + minutesLeft + " minutes") ;
        }
    }
    return;
   }
}
