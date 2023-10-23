public class CreditCard {
   public static void main(String[] args) {
    if (args.length != 1){
        System.out.println("Please enter a credit card number") ;
        return;
    }
    String creditCardString = args[0];
    int digits = creditCardString.length();
    int[] creditCardArray = new int[digits];
    int valid = 1; // 0 for invalid, 1 for valid
    for (int i = 0; i < digits; i++) {
        creditCardArray[i] = Character.getNumericValue(creditCardString.charAt(i));
    }
    //RULE 1
    if (creditCardArray[0] == 4 || creditCardArray[0] == 5){
        if (digits != 16){
            valid = 0;
        }
    }
    else if ((creditCardArray[0] == 3 && creditCardArray[1] == 7) || (creditCardArray[0] == 3 && creditCardArray[1] == 4) ){
        if (digits != 15){
            valid = 0;
        }
    }
    else{
        valid = 0;
    }
    if (valid == 0){
        System.out.println("Invalid");
        return;
    }
    // RULE 2
    int digit3Remainder = creditCardArray[2] % 2;
    int digit4Remainder = creditCardArray[3] % 2;
    if (digit3Remainder == 0){
        if (digit4Remainder != 1){
            valid = 0;
        }
    }
    else{
        if (digit4Remainder != 0){
            valid = 0;
        }
    }
    if (valid == 0){
        System.out.println("Invalid");
        return;
    }
    // RULE 3
    if (creditCardArray[7] != 0){
        if (creditCardArray[7] != creditCardArray[8] + creditCardArray[9]){
            valid = 0;
        }
    }
    if (valid == 0){
        System.out.println("Invalid");
        return;
    }
    //RULE 4
    int arraySum = 0;
    for (int i = 0; i < digits; i++){
        arraySum = arraySum + creditCardArray[i];
    }
    if ((arraySum % 4) != 0){
        valid = 0;
    }
    if (valid == 0){
        System.out.println("Invalid");
        return;
    }
    //RULE 5
    int headTailSum = 10 * creditCardArray[0] + creditCardArray[1] + 10 * creditCardArray[digits-2] + creditCardArray[digits-1];
    if (headTailSum != 50 && headTailSum != 100){
        valid = 0;
    }
    if (valid == 0){
        System.out.println("Invalid");
        return;
    }
    if (valid == 1){
        System.out.println("Valid");
        return;
    }
   }
}