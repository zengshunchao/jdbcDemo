package com.zengshunchao.RSAdemo;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;

/**
 * 生成公钥
 */
public class CreatPublicKey {

    public static final String CHARSET = "UTF-8";

    /**
     * 生成密钥对
     *
     * @return
     */
    public static Map<String, String> pulicsKey() {
        KeyPairGenerator keyPairGenerator;
        Map<String, String> map = new HashMap<>();
        try {
            //为RSA算法创建一个KeyPairGenerator对象
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //初始化KeyPairGenerator对象的密钥长度（一般为1024比较好）
            keyPairGenerator.initialize(1024);

            //生成密钥对
            KeyPair pair = keyPairGenerator.generateKeyPair();
            //获取公钥
            Key publicKey = pair.getPublic();
            //使用org.apache.commons.codec.binary.Base64包中Base64进行编码
            String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());

            //获取密钥
            Key privateKey = pair.getPrivate();
            String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());

            map.put("publicKey", publicKeyStr);
            map.put("privateKey", privateKeyStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     *
     * @param privateKey 密钥字符串（经过base64编码）
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     *
     * @param data      加密字符串
     * @param publicKey 公钥
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(RSASplit.rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     *
     * @param data      解密字符串
     * @param publicKey 公钥
     * @return
     */
    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(RSASplit.rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     *
     * @param data       加密字符串
     * @param privateKey 私钥
     * @return
     */
    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(RSASplit.rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("私钥加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param data       解密字符串
     * @param privateKey 私钥
     * @return
     */
    public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(RSASplit.rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("私钥解密字符串[" + data + "]时遇到异常", e);
        }
    }


    public static void main(String[] args) throws Exception {
        Map<String, String> map = CreatPublicKey.pulicsKey();
//        System.out.println(map.get("publicKey") + "\t" + map.get("privateKey"));
//        String str = "guaiguai520.913";
//
//        String publicEn = CreatPublicKey.publicEncrypt(str, CreatPublicKey.getPublicKey(map.get("publicKey")));
//        System.out.println("密文:" + "\n" + publicEn);
//
//        String privateEn = CreatPublicKey.privateDecrypt(publicEn, CreatPublicKey.getPrivateKey(map.get("privateKey")));
//        System.out.println("解密后的文字:" + "\n" + privateEn);

        String publicKeyString = new String(Base64.encodeBase64(getPublicKey(map.get("publicKey")).getEncoded()));
//        System.out.println(publicKeyString);
//        System.out.println("\n" + getPrivateKey(map.get("privateKey")).getModulus());

//        System.out.println(new String(Base64.encodeBase64(getPrivateKey(map.get("privateKey")).getEncoded())));
        System.out.println(getPublicKey(Base64.encodeBase64URLSafeString("12345678".getBytes())));
    }
}
