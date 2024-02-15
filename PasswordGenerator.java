import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class PasswordGenerator {

    private static final int PASSWORD_LENGTH = 12; // Minimum recommended password length
    private static final String LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{};':\"\\|,<.>/?";

    public static void main(String[] args) {

        SecureRandom random = new SecureRandom(); // Use SecureRandom for better security

        StringBuilder password = new StringBuilder();

        // Include at least one character from each category
        password.append(randomChar(random, LOWER_CASE_CHARS));
        password.append(randomChar(random, UPPER_CASE_CHARS));
        password.append(randomChar(random, NUMBERS));
        password.append(randomChar(random, SPECIAL_CHARS));

        // Fill remaining characters with any combination
        for (int i = password.length(); i < PASSWORD_LENGTH; i++) {
            String charSet = combineCharSets(LOWER_CASE_CHARS, UPPER_CASE_CHARS, NUMBERS, SPECIAL_CHARS);
            password.append(randomChar(random, charSet));
        }

        // Shuffle the characters for added randomness
        char[] passwordChars = password.toString().toCharArray();
        shuffleArray(passwordChars, random);
        password = new StringBuilder(String.valueOf(passwordChars));

        System.out.println("Generated password: " + password);

        // Remember, NEVER store or display passwords in plain text!
        // Consider using encryption or secure password storage mechanisms.
    }

    private static char randomChar(SecureRandom random, String charSet) {
        return charSet.charAt(random.nextInt(charSet.length()));
    }

    private static String combineCharSets(String... charSets) {
        StringBuilder combined = new StringBuilder();
        for (String charSet : charSets) {
            combined.append(charSet);
        }
        return combined.toString();
    }

    private static void shuffleArray(char[] array, SecureRandom random) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
