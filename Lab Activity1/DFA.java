import java.util.Scanner;

public class DFA {
     private enum states{
        Q0,Q1,Q2
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("DFA String Acceptance Checker \nLanguage: If binary string ends with '01'\nInput Binary String: ");
        String BinaryInput = scan.nextLine();

        if (!BinaryInput.matches("[01]*")) {
            System.out.println("Invalid Input, pleas Input Binary digits only(0,1).");
        }

        boolean accepted = checkFDA(BinaryInput);

        if(accepted) {
            System.out.println("Input: " + BinaryInput + " Result: String is accepted - The string ended with '01'");
        } else {
            System.out.println("Input: " + BinaryInput +" Result: String is not accepted - The string did not end with '01'");
        }

        scan.close();
    }
     private static boolean checkFDA(String BinaryInput) {
        states currentstate = states.Q0;
        
        for (char x : BinaryInput.toCharArray()) {
            switch(currentstate) {
                case Q0:
                    if (x == '0') {
                        currentstate = states.Q1;
                    } else if (x == '1') {
                        currentstate = states.Q0;
                    } break;
                
                case Q1:
                    if (x == '0') {
                        currentstate = states.Q1;
                    } else if (x == '1'){
                        currentstate = states.Q2;
                    } break;

                case Q2:
                    if(x == '0'){
                        currentstate = states.Q1;
                    } else if (x == '1') {
                        currentstate = states.Q0;
                    }
            }
        }
        
        //String is accepted when it ends in Q2
        return currentstate == states.Q2;
     }
}
