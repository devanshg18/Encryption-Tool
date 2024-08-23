package main.java.com.example.cli;

import javax.crypto.SecretKey;
import main.java.com.example.encryption.FileEncryptor;
import main.java.com.example.encryption.FileDecryptor;
import main.java.com.example.encryption.EncryptionUtils;

import java.io.File;
import java.util.Scanner;

public class CommandLineInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private final FileEncryptor fileEncryptor = new FileEncryptor();
    private final FileDecryptor fileDecryptor = new FileDecryptor();

    public void start() {
        System.out.println("Welcome to the File Encryption Tool");
        System.out.println("Choose an option: ");
        System.out.println("1. Encrypt a file");
        System.out.println("2. Decrypt a file");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                encryptFile();
                break;
            case 2:
                decryptFile();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void encryptFile() {
        try {
            System.out.print("Enter the path of the file to encrypt: ");
            String inputPath = scanner.nextLine();
            File inputFile = new File(inputPath);

            System.out.print("Enter the path for the encrypted output file: ");
            String outputPath = scanner.nextLine();
            File outputFile = new File(outputPath);

            // Use the FileEncryptor instance to generate the key and encrypt the file
            SecretKey secretKey = fileEncryptor.generateKey();
            fileEncryptor.encryptFile(inputFile, outputFile, secretKey);
            
            String encodedKey = EncryptionUtils.encodeKey(secretKey);
            System.out.println("File encrypted successfully!");
            System.out.println("Secret Key (store this securely): " + encodedKey);
        } catch (Exception e) {
            System.err.println("Error during encryption: " + e.getMessage());
        }
    }

    private void decryptFile() {
        try {
            System.out.print("Enter the path of the file to decrypt: ");
            String inputPath = scanner.nextLine();
            File inputFile = new File(inputPath);

            System.out.print("Enter the path for the decrypted output file: ");
            String outputPath = scanner.nextLine();
            File outputFile = new File(outputPath);

            System.out.print("Enter the secret key: ");
            String keyString = scanner.nextLine();
            SecretKey secretKey = EncryptionUtils.decodeKey(keyString);

            fileDecryptor.decryptFile(inputFile, outputFile, secretKey);
            System.out.println("File decrypted successfully!");
        } catch (Exception e) {
            System.err.println("Error during decryption: " + e.getMessage());
        }
    }
}