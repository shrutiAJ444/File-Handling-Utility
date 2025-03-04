import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * A utility class for file handling operations: reading, writing, and modifying text files.
 */
public class FileHandlingUtility {
    
    /**
     * Writes content to a file. If the file exists, it will be overwritten.
     * @param filePath Path to the file.
     * @param content Content to write.
     */
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Reads content from a file and returns it as a String.
     * @param filePath Path to the file.
     * @return Content of the file as a String.
     */
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }

    /**
     * Modifies a file by replacing a specific word with another.
     * @param filePath Path to the file.
     * @param targetWord Word to be replaced.
     * @param replacementWord Word to replace with.
     */
    public static void modifyFile(String filePath, String targetWord, String replacementWord) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    writer.write(line.replace(targetWord, replacementWord));
                    writer.newLine();
                }
            }
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }

    /**
     * Demonstrates file operations.
     */
    public static void main(String[] args) {
        String filePath = "sample.txt";
        
        // Writing to a file
        writeFile(filePath, "Hello, this is a sample text file.");
        
        // Reading from the file
        System.out.println("File Content:");
        System.out.println(readFile(filePath));
        
        // Modifying the file
        modifyFile(filePath, "sample", "modified");
        
        // Reading the modified content
        System.out.println("Modified File Content:");
        System.out.println(readFile(filePath));
    }
}
