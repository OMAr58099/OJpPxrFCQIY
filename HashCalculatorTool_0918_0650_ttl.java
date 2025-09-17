// 代码生成时间: 2025-09-18 06:50:11
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 哈希值计算工具类，用于计算字符串的哈希值。
 * 遵循JAVA最佳实践，确保代码的可维护性和可扩展性。
 */
public class HashCalculatorTool {

    /**
     * 计算字符串的哈希值。
     * 
     * @param input 需要计算哈希值的字符串
     * @param algorithm 哈希算法（如MD5, SHA-1等）
     * @return 返回计算后的哈希值
     */
    public static String calculateHash(String input, String algorithm) {
        try {
            // 获取指定算法的MessageDigest实例
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // 更新要计算的数据
            digest.update(input.getBytes());

            // 计算哈希值
            byte[] hashBytes = digest.digest();

            // 将哈希值转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 错误处理：指定的算法不存在
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 主方法，用于测试哈希值计算工具。
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        String input = "Hello, MyBatis!";
        String algorithm = "SHA-256"; // 也可以使用MD5, SHA-1等其他算法

        String hashValue = calculateHash(input, algorithm);

        if (hashValue != null) {
            System.out.println("Input: " + input);
            System.out.println("Hash Value: " + hashValue);
        } else {
            System.out.println("Error: Unable to calculate hash value.