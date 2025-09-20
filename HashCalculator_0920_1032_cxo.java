// 代码生成时间: 2025-09-20 10:32:00
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;

public class HashCalculator {

    /**
     * Calculates the hash (SHA-256) of the given input string.
     *
     * @param input The input string to be hashed.
     * @return The hash value as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public static String calculateSHA256Hash(String input) throws NoSuchAlgorithmException {
        // Get the SHA-256 MessageDigest instance
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Update the digest using the input string bytes
        digest.update(input.getBytes(StandardCharsets.UTF_8));

        // Calculate the hash
        byte[] hashBytes = digest.digest();

        // Convert the hash bytes to a hexadecimal string
        return bytesToHex(hashBytes);
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param bytes The byte array to convert.
     * @return The hexadecimal string representation of the byte array.
     */
    private static String bytesToHex(byte[] bytes) {
        try (Formatter formatter = new Formatter()) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }

    /**
     * Main method for testing the HashCalculator.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            String input = "Hello, World!";
            String hash = calculateSHA256Hash(input);
            System.out.println("Input: " + input);
            System.out.println("Hash: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: SHA-256 algorithm is not available.");
            e.printStackTrace();
        }
    }
}
