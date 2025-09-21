// 代码生成时间: 2025-09-21 11:27:57
import org.apache.commons.codec.digest.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * 哈希值计算工具服务类
 *
 * @author Your Name
 * @version 1.0
 */
public class HashCalculatorService {

    /**
     * 根据给定的输入字符串和哈希算法生成哈希值
     *
     * @param input 输入字符串
     * @param algorithm 哈希算法名称
     * @return 包含哈希值的Optional对象
     */
    public Optional<String> calculateHash(String input, String algorithm) {
        try {
            // 使用MessageDigest获取指定算法的实例
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            // 计算哈希值
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            // 将字节数组转换为十六进制字符串
            String hexHash = DigestUtils.bytesToHex(hashBytes);
            return Optional.of(hexHash);
        } catch (NoSuchAlgorithmException e) {
            // 如果指定的算法不存在，则返回一个空的Optional对象
            System.err.println("Error: Unsupported hash algorithm.");
            return Optional.empty();
        } catch (Exception e) {
            // 处理其他可能的异常
            System.err.println("Error: Failed to calculate hash.");
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
