// 代码生成时间: 2025-10-01 20:06:43
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A utility class to read and write binary files.
 */
public class BinaryFileReadWriteTool {

    /**
     * Reads a binary file and returns its content as byte array.
     *
     * @param filePath The path to the binary file.
     * @return A byte array containing the file's content.
     * @throws IOException If an I/O error occurs reading the file.
     */
    public static byte[] readFileAsBytes(String filePath) throws IOException {
        // Check if the file exists
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("File not found: " + filePath);
        }
        
        // Read the file into a byte array
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * Writes a byte array to a binary file.
     *
     * @param filePath The path to the binary file.
     * @param data     The byte array to write.
     * @throws IOException If an I/O error occurs writing the file.
     */
    public static void writeFileFromBytes(String filePath, byte[] data) throws IOException {
        // Write the byte array to the file
        Files.write(Paths.get(filePath), data);
    }

    /**
     * Main method for demonstration purposes.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Read a binary file
            String readFilePath = "example.bin";
            byte[] fileContent = readFileAsBytes(readFilePath);
            System.out.println("File read successfully. Size: " + fileContent.length + " bytes");

            // Write a byte array to a binary file
            String writeFilePath = "output.bin";
            byte[] dataToWrite = new byte[]{1, 2, 3, 4, 5}; // Example data
            writeFileFromBytes(writeFilePath, dataToWrite);
            System.out.println("File written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}