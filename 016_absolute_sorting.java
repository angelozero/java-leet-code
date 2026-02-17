/**
Objective
    Create a Java program that sorts an array of integers in ascending order (e.g., input [3, 2, 1, 4, 5] becomes [1, 2, 3, 4, 5]) 
    while adhering to the strictest programming constraints possible. 
    You must solve the problem using only Arithemtic, Bitwise Operations, and Recursion.

The Constraints (The "Forbidden" List)
    No Iteration (Loops): You are strictly forbidden from using for, while, or do-while loops.
    No Branching (Conditionals): You cannot use if, else, or switch statements.
    No Ternary Operators: You cannot use the (condition ? true : false) syntax, as it is a shorthand for an if statement.
    No External Libraries/APIs:
    No java.util.stream (Streams API).
    No java.util.Collections.sort().
    No java.util.Arrays.sort().
    No java.util.TreeSet or PriorityQueue.
    No Comparison Logic: You cannot use the result of a comparison (like a > b) to control the flow of the program directly via a branch.

Technical Hint
    To succeed, you must explore:
    Logical Short-circuiting: Using && or || as a mechanism to stop recursion.
    Sign-Bit Extraction: Using bit-shifting (>> 31) to determine the relationship between two numbers.
    Deterministic Arithmetic: Creating mathematical formulas that result in the "minimum" or "maximum" value without asking the CPU to "choose" a path.
 */

import java.util.Arrays;
import java.util.Optional;

public class AbsoluteSorting {
    public static void main(String[] args) {
        int[] data = {3, 2, 1, 4, 5};
        
        // Start the recursive sorting process
        sort(data, data.length);
        
        // Final output using Optional to avoid an explicit 'if' for printing
        Optional.of(data).ifPresent(a -> System.out.println(Arrays.toString(a)));
    }

    /**
     * Recursive "Outer Loop"
     * Uses logical short-circuiting (&&) to replace the 'if' exit condition.
     */
    static boolean sort(int[] arr, int n) {
        return (n > 1) && traverse(arr, 0, n) && sort(arr, n - 1);
    }

    /**
     * Recursive "Inner Loop"
     * Performs the comparison and swap using purely mathematical bit manipulation.
     */
    static boolean traverse(int[] arr, int i, int n) {
        int current = arr[i];
        int next = arr[i + 1];

        // 1. Calculate difference
        int diff = current - next;

        // 2. Extract Sign Bit (31st bit)
        // sign = 1 if current < next (negative diff)
        // sign = 0 if current >= next (positive/zero diff)
        int sign = (diff >> 31) & 1;

        // 3. Create a Swap Mask
        // If current > next, sign is 0. (1 - 0) * diff = diff.
        // If current < next, sign is 1. (1 - 1) * diff = 0.
        int mask = (1 - sign) * diff;

        // 4. Apply the swap without a temporary variable or 'if'
        // If mask is 0, values stay the same. 
        // If mask is 'diff', current becomes (current - (current - next)) = next.
        arr[i] = current - mask;
        arr[i + 1] = next + mask;

        // 5. Continue recursion for the next index
        return (i + 1 < n - 1) && traverse(arr, i + 1, n);
    }
}