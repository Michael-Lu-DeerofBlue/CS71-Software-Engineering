public class Distance {
   public static void main(String[] args) {
    if (args.length != 4){
        System.out.println("Please specify four values") ;
        return;
    }
    Double x1 = Double.parseDouble(args[0]); 
    Double y1 = Double.parseDouble(args[1]); 
    Double x2 = Double.parseDouble(args[2]); 
    Double y2 = Double.parseDouble(args[3]); 
    if (x1 < -180.0 || x2 < -180.0 || y1 < -180.0 || y2 < -180.0 || x1 > 180.0 || x2 > 180.0 || y1 > 180.0 || y2 > 180.0){
        System.out.println("Please enter a valid lat/lon value") ;
        return;
    }
    x1 = x1 * (Math.PI / 180.0);
    y1 = y1 * (Math.PI / 180.0);
    x2 = x2 * (Math.PI / 180.0);
    y2 = y2 * (Math.PI / 180.0);
    Double E = 3986.0;
    Double d = E * Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
    System.out.println(d + " miles") ;
   }
}