import java.util.*;

public class NFA {
    //State Initialization
    private enum State {
        Q0,Q1,Q2
    }

private static Map<State, Map<Character, Set<State>>> Transitions() {
    Map<State, Map<Character, Set<State>>> nfaTransitions = new HashMap<>();

        // Q0 transitions
        Map<Character, Set<State>> q0Transitions = new HashMap<>();
        q0Transitions.put('a', new HashSet<>(Arrays.asList(State.Q0, State.Q1)));
        q0Transitions.put('b', new HashSet<>(Arrays.asList(State.Q0)));
        nfaTransitions.put(State.Q0, q0Transitions);
        
        // Q1 transitions
        Map<Character, Set<State>> q1Transitions = new HashMap<>();
        q1Transitions.put('a', new HashSet<>(Arrays.asList(State.Q1)));
        q1Transitions.put('b', new HashSet<>(Arrays.asList(State.Q2)));
        nfaTransitions.put(State.Q1, q1Transitions);
        
        // Q2 transitions
        Map<Character, Set<State>> q2Transitions = new HashMap<>();
        q2Transitions.put('a', new HashSet<>(Arrays.asList(State.Q2)));
        q2Transitions.put('b', new HashSet<>(Arrays.asList(State.Q2)));
        nfaTransitions.put(State.Q2, q2Transitions);
        
        return nfaTransitions;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

         Map<State, Map<Character, Set<State>>> nfaTransitions = Transitions();

        System.out.println("NFA String Acceptance Checker \nLanguage: If string contains 'ab' as a substring\nInput String(a,b): ");
        String Input = scan.nextLine();

        //Checks if there are any characters other than a/b
        if (!Input.matches("[ab]*")) {
            System.out.println("Invalid Input, please input from alphabet(a,b) only.");
            scan.close();
            return;
        }

        boolean accepted = checkNFA(Input, nfaTransitions);

        if(accepted) {
            System.out.println("Input: " + Input + " Result: String is accepted - The string contains the substring 'ab'");
        } else {
            System.out.println("Input: " + Input +" Result: String is not accepted - The string does not contain the substring 'ab'");
        }

        scan.close();
    }

    //Queue to check
private static boolean checkNFA(String BinaryInput,  Map<State, Map<Character, Set<State>>> transitions) {
    Queue<State> queue = new LinkedList<>();
    queue.add(State.Q0);
    
    for (char x : BinaryInput.toCharArray()) {
        int size = queue.size();
        Set<State> nextStates = new HashSet<>();
        
        for (int i = 0; i < size; i++) {
            State current = queue.poll();
            Map<Character, Set<State>> currentTransitions = transitions.get(current);
            if (currentTransitions != null && currentTransitions.containsKey(x)) {
                nextStates.addAll(currentTransitions.get(x));
            }
        }
        
        queue.addAll(nextStates);
        
        if (nextStates.contains(State.Q2)) {
            return true;
        }
    }
    //Accepts string when it contains substring ab
    return queue.contains(State.Q2);
}
}
