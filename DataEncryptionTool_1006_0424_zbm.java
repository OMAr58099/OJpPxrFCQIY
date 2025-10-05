// 代码生成时间: 2025-10-06 04:24:43
public class DataEncryptionTool {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "YourSecretKeyHere"; // Replace with your actual encryption key

    /**
     * Encrypts the given plain text using AES encryption.
     * 
     * @param plainText The text to be encrypted.
     * @return The encrypted text as a Base64 encoded string.
     * @throws Exception If encryption fails.
     */
    public static String encrypt(String plainText) throws Exception {
        try {
            // Initialize Cipher with AES algorithm
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            Key key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Encrypt the plain text
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            // Return the encrypted bytes as a Base64 encoded string
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Encryption failed: " + e.getMessage(), e);
        }
    }

    /**
     * Decrypts the given encrypted text using AES decryption.
     * 
     * @param encryptedText The text to be decrypted, Base64 encoded.
     * @return The decrypted plain text.
     * @throws Exception If decryption fails.
     */
    public static String decrypt(String encryptedText) throws Exception {
        try {
            // Initialize Cipher with AES algorithm
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            Key key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Decrypt the encrypted text
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            // Return the decrypted bytes as a string
            return new String(decryptedBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Decryption failed: " + e.getMessage(), e);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            String plainText = "Hello, World!";
            String encryptedText = encrypt(plainText);
            System.out.println("Encrypted: " + encryptedText);

            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
