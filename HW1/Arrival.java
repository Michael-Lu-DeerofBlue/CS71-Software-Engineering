public class Arrival {
   public static void main(String[] args) {
    if (args.length != 4){
        System.out.println("Please specify four positive integers") ;
        return;
    }
    int hours = Integer.parseInt(args[0]); 
    int minutes = Integer.parseInt(args[1]); 
    int distance = Integer.parseInt(args[2]); 
    int speed = Integer.parseInt(args[3]); 
    if (hours < 0 || minutes < 0 || distance < 0 || speed < 0){
        System.out.println("Please specify four positive integers") ;
        return;
    }
    if (hours > 23){
        System.out.println("Please specify an hour between 0-23") ;
        return;
    }
    if (minutes > 59){
        System.out.println("Please specify a minute between 0-59") ;
        return;
    }
    int leftDistance = distance % speed;
    int deltaHours = distance / speed;
    int deltaMinutes = leftDistance * 60 / speed;
    hours = hours + deltaHours;
    minutes = minutes + deltaMinutes;
    if (minutes >= 60){
        minutes = minutes - 60;
        hours = hours + 1;
    }
    if (hours >= 24){
        hours = hours - 24;
    }
    String hoursString = Integer.toString(hours); 
    String minutesString = Integer.toString(minutes); 
    if (minutes < 10){
        minutesString = "0" + minutesString;
    }
    if (hours < 10){
        hoursString = "0" + hoursString;
    }
    System.out.println("Arrival Time is " + hoursString + ":" + minutesString) ;
    }
}