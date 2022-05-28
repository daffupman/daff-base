package io.daff.utils.crypto;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 密码加密工具类。主要步骤：
 * - 对明文sha512
 * - 再使用BCrypt加密
 * - 最后再加一层AES
 *
 * @author daffupman
 * @since 2020/7/19
 */
public class StrongCryptoUtil {

    /**
     * 加密
     *
     * @param plain 明文
     * @param salt  盐
     * @return 密文
     */
    public static String encrypt(String plain, String salt) {
        try {
            // sha512加密
            String sha512Password = sha512(plain, salt);

            // BCrypt加密
            String bCryptPassword = BCrypt.hashpw(sha512Password, salt);

            // AES加密
            return AESUtils.encrypt(bCryptPassword, AESUtils.ASSETS_DEV_PWD_FIELD);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证输入的密码和数据库的密码是否一致
     *
     * @param plain       输入的明文密码
     * @param aesPassword 数据库中存储的密文
     * @return 是否一致
     */
    public static boolean validate(String plain, String aesPassword, String salt) {
        // aes 解密
        aesPassword = AESUtils.decrypt(aesPassword, AESUtils.ASSETS_DEV_PWD_FIELD);

        // BCrypt密码验证
        // sha512加密
        plain = sha512(plain, salt);

        // BCrypt密码检查
        return BCrypt.checkpw(plain, aesPassword);
    }

    /**
     * 使用BCrypt生成盐
     */
    public static String genSalt() {
        return BCrypt.gensalt(12, new SecureRandom());
    }

    /**
     * sha512加密
     */
    private static String sha512(String plain, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(plain.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}
