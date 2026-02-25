import java.util.Scanner;

public class UseCase4PalindromeCheckerApp {
    public static void main(String[] args) {
        String appName = "Palindrome Checker App";
        String appVersion = "v4.0";

        System.out.println("========================================");
        System.out.println("Welcome to " + appName);
        System.out.println("Application Name    : " + appName);
        System.out.println("Application Version : " + appVersion);
        System.out.println("========================================");
        System.out.println("Type text to check palindrome (or type EXIT).");
        System.out.println();

        // UC1 / UC2 fixed input
        runFixedInputUseCase();

        // UC3: Palindrome Check Using String Reverse
        runUC3Example();
        // UC4: Character Array Based Palindrome Check
        runUC4Example();

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

            boolean strictResult = isPalindromeStrict(input);
            boolean normalizedResult = isPalindromeIgnoreCaseAndNonAlnum(input);
            boolean reverseResult = isPalindromeUsingReverse(input);
            boolean charArrayResult = isPalindromeUsingCharArray(input);

            System.out.println("Strict check (exact characters)              : " + strictResult);
            System.out.println("Normalized check (ignore case/symbols/spaces): " + normalizedResult);
            System.out.println("UC3 - Reverse method check                   : " + reverseResult);
            System.out.println("UC4 - Char array two-pointer check           : " + charArrayResult);
        }

        scanner.close();
        System.out.println("Exiting Palindrome Checker App.");
    }

    // UC1 / UC2 fixed input
    static void runFixedInputUseCase() {
        String input = "abba";
        String reversed = reverseText(input);

        System.out.println("Use Case (fixed input)");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (input.equals(reversed) ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // UC3: Palindrome Check Using String Reverse (dynamic example)
    static void runUC3Example() {
        String input = "racecar";
        String reversed = reverseText(input);

        System.out.println("UC3 - Palindrome Check Using String Reverse");
        System.out.println("Input String : " + input);
        System.out.println("Reversed     : " + reversed);
        System.out.println("Result       : " + (input.equals(reversed) ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // UC4: Character Array Based Palindrome Check
    static void runUC4Example() {
        String input = "level";
        boolean result = isPalindromeUsingCharArray(input);

        System.out.println("UC4 - Character Array Based Palindrome Check");
        System.out.println("Input String : " + input);
        System.out.println("Result       : " + (result ? "Palindrome" : "Not a palindrome"));
        System.out.println();
    }

    // Reverse helper
    static String reverseText(String text) {
        String reversed = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed = reversed + text.charAt(i);
        }
        return reversed;
    }

    // Strict palindrome check
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

    // Ignore case & non-alphanumeric
    static boolean isPalindromeIgnoreCaseAndNonAlnum(String text) {
        String cleaned = text.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        return isPalindromeStrict(cleaned);
    }

    // UC3 palindrome check using reverse
    static boolean isPalindromeUsingReverse(String text) {
        String reversed = reverseText(text);
        return text.equals(reversed);
    }

    // UC4 palindrome check using char[] and two pointers
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

    // Removes control artifacts that can appear in piped console input on Windows.
    static String sanitizeInput(String text) {
        return text.replace("\uFEFF", "").replace("\r", "");
    }
}
