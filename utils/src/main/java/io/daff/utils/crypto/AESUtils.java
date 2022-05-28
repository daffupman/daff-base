package io.daff.utils.crypto;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES加密解密
 *
 * @author daffupman
 * @since 2020/6/30
 */
public class AESUtils {

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
      */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    /**
     * 自定义密码
     */
    public static final String ASSETS_DEV_PWD_FIELD = "default-password";

    public static String getAssetsDevPwdField() {
        return ASSETS_DEV_PWD_FIELD;
    }

    /**
     * AES 加密操作
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64Utils.encodeToString(result);
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * AES 解密操作
     */
    public static String decrypt(String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));
            String s = new String(result, StandardCharsets.UTF_8);
            return s;
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 生成加密秘钥
     */
    private static SecretKeySpec getSecretKey(String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            //AES 要求密钥长度为 128
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        String origin = "12345";
        String encrypt = AESUtils.encrypt(origin, AESUtils.ASSETS_DEV_PWD_FIELD);
        String decrypt = AESUtils.decrypt(encrypt, AESUtils.ASSETS_DEV_PWD_FIELD);
        System.out.println(origin);
        System.out.println(encrypt);
        System.out.println(decrypt);
    }
}
