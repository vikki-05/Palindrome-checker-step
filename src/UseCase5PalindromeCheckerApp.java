import java.util.Scanner;
import java.util.Stack;

public class UseCase5PalindromeCheckerApp {
    public static void main(String[] args) {
        String appName = "Palindrome Checker App";
        String appVersion = "v5.0";

        System.out.println("========================================");
        System.out.println("Welcome to " + appName);
        System.out.println("Application Name    : " + appName);
        System.out.println("Application Version : " + appVersion);
        System.out.println("========================================");
        System.out.println("Type text to check palindrome (or type EXIT).");
        System.out.println();

        // UC1 to UC5 examples
        runUC1Example();
        runUC2Example();
        runUC3Example();
        runUC4Example();
        runUC5Example();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Enter a string to check palindrome (or type EXIT):");
            if (!scanner.hasNextLine()) {
                System.out.println("Input stream closed. Exiting app.");
                break;
            }

            String input = sanitizeInput(scanner.nextLine());
            if ("EXIT".equalsIgnoreCase(input)) {
                break;
            }

            if (input.trim().isEmpty()) {
                System.out.println("Input is empty. Please enter valid text.");
                continue;
            }

            boolean uc1Result = isPalindromeStrict(input);
            boolean uc2Result = isPalindromeIgnoreCaseAndNonAlnum(input);
            boolean uc3Result = isPalindromeUsingReverse(input);
            boolean uc4Result = isPalindromeUsingCharArray(input);
            boolean stackResult = isPalindromeUsingStack(input);

            System.out.println("UC1 - Strict palindrome check                 : " + uc1Result);
            System.out.println("UC2 - Ignore case/symbols/spaces             : " + uc2Result);
            System.out.println("UC3 - Reverse method check                    : " + uc3Result);
            System.out.println("UC4 - Char array two-pointer check            : " + uc4Result);
            System.out.println("UC5 - Stack-based palindrome check            : " + stackResult);
        }

        scanner.close();
        System.out.println("Exiting Palindrome Checker App.");
    }

    // UC1: Basic palindrome check with fixed input
    static void runUC1Example() {
        String input = "abba";
        boolean result = isPalindromeStrict(input);

        System.out.println("UC1 - Basic Palindrome Check");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (result ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // UC2: Ignore case, spaces and symbols
    static void runUC2Example() {
        String input = "A man, a plan, a canal: Panama";
        boolean result = isPalindromeIgnoreCaseAndNonAlnum(input);

        System.out.println("UC2 - Normalized Palindrome Check");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (result ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // UC3: Palindrome check using manual reverse
    static void runUC3Example() {
        String input = "racecar";
        boolean result = isPalindromeUsingReverse(input);

        System.out.println("UC3 - Palindrome Check Using String Reverse");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (result ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // UC4: Palindrome check using char[] and two pointers
    static void runUC4Example() {
        String input = "level";
        boolean result = isPalindromeUsingCharArray(input);

        System.out.println("UC4 - Character Array Based Palindrome Check");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (result ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // UC5: Stack-Based Palindrome Checker
    static void runUC5Example() {
        String input = "madam";
        boolean result = isPalindromeUsingStack(input);

        System.out.println("UC5 - Stack-Based Palindrome Checker");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (result ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    static boolean isPalindromeStrict(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static boolean isPalindromeIgnoreCaseAndNonAlnum(String text) {
        String cleaned = text.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        return isPalindromeStrict(cleaned);
    }

    static String reverseText(String text) {
        String reversed = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed = reversed + text.charAt(i);
        }
        return reversed;
    }

    static boolean isPalindromeUsingReverse(String text) {
        String reversed = reverseText(text);
        return text.equals(reversed);
    }

    static boolean isPalindromeUsingCharArray(String text) {
        char[] characters = text.toCharArray();
        int left = 0;
        int right = characters.length - 1;

        while (left < right) {
            if (characters[left] != characters[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Push all characters to stack, then pop for reverse-order comparison.
    static boolean isPalindromeUsingStack(String text) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            stack.push(text.charAt(i));
        }

        for (int i = 0; i < text.length(); i++) {
            char popped = stack.pop();
            if (text.charAt(i) != popped) {
                return false;
            }
        }

        return true;
    }

    // Removes control artifacts that can appear in piped console input on Windows.
    static String sanitizeInput(String text) {
        return text.replace("\uFEFF", "").replace("\r", "");
    }
}
