package main.java.com.swift.app;

public class Encryption {
    // Encryption method
    public static String encrypt(String message) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char originalChar = message.charAt(i);
            char encryptedChar;
            if (Character.isLetter(originalChar)) {
                if (Character.isUpperCase(originalChar)) {
                    encryptedChar = (char) ('Z' - (originalChar - 'A'));
                } else {
                    encryptedChar = (char) ('z' - (originalChar - 'a'));
                }
            } else {
                encryptedChar = originalChar;
            }
            encryptedMessage.append(encryptedChar);
        }
        return encryptedMessage.toString();
    }

    // Decryption method
    public static String decrypt(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char encryptedChar = encryptedMessage.charAt(i);
            char decryptedChar;
            if (Character.isLetter(encryptedChar)) {
                if (Character.isUpperCase(encryptedChar)) {
                    decryptedChar = (char) ('Z' - (encryptedChar - 'A'));
                } else {
                    decryptedChar = (char) ('z' - (encryptedChar - 'a'));
                }
            } else {
                decryptedChar = encryptedChar;
            }
            decryptedMessage.append(decryptedChar);
        }
        return decryptedMessage.toString();
    }

//    public static void main(String[] args) {
//        String originalMessage = "Hello, how are you?";
//        String encryptedMessage = encrypt(originalMessage);
//        System.out.println("Encrypted: " + encryptedMessage);
//
//        String decryptedMessage = decrypt(encryptedMessage);
//        System.out.println("Decrypted: " + decryptedMessage);
//    }
}
