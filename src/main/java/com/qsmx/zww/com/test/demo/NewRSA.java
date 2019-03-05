package com.qsmx.zww.com.test.demo;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zww on 2019-03-04.
 */
public class NewRSA {

    /**
     * @描述： 用jdk自带Base64，Cipher进行公钥，私钥的生成 实现RSA 加密解密算法
     * @参数： []
     * @返回值： java.util.Map<java.lang.String       ,       java.lang.String>
     * @创建人： zhangww
     * @创建时间： 2019-03-05
     * @修改人和其它信息：
     */
    //得到ras算法公钥，私钥。
    public Map<String, String> getRasKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGen.genKeyPair();
        PublicKey newPublicKey = keyPair.getPublic();
        PrivateKey newPrivateKey = keyPair.getPrivate();
        String publicKey = Base64.getEncoder().encodeToString(newPublicKey.getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(newPrivateKey.getEncoded());
        System.out.println("新公钥:" + publicKey);
        System.out.println("新私钥:" + privateKey);
        Map<String, String> rasMap = new HashMap<>();
        rasMap.put("publicKey", publicKey);
        rasMap.put("privateKey", privateKey);
        return rasMap;
    }

    /**
     * @描述： 通过公钥，进行Rsa算法加密 返回加密串
     * @参数： [str 待加密字符串, publicKey RSA公钥, cipher 加密对象]
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-03-05
     * @修改人和其它信息：
     */
    //通过公钥加密
    public String encryption(String str, String publicKey, Cipher cipher) throws Exception {
        //通过base64 解码公钥
        byte[] publicKeyByte = Base64.getDecoder().decode(publicKey);
        RSAPublicKey publicKey1 = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyByte));
        /*//RSA加密
        Cipher cipher = Cipher.getInstance("RSA");*/
        cipher.init(Cipher.ENCRYPT_MODE, publicKey1);//加密模式
        String ciphertext = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        System.out.println("RAS加加密后：" + ciphertext);
        return ciphertext;
    }

    /**
     * @描述： 通过RSA私钥进行解密，返回解密后的串
     * @参数： [miwen 密文, privateKey RSA私钥, cipher 密码类]
     * @返回值： java.lang.String
     * @创建人： zhangww
     * @创建时间： 2019-03-05
     * @修改人和其它信息：
     */
    //通过私钥解密
    public String decrypt(String miwen, String privateKey, Cipher cipher) throws Exception {
        //通过base64 解码私钥
        byte[] privateKeyByte = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey privateKey1 = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
        //先解密密文
        byte[] bytes = Base64.getDecoder().decode(miwen.getBytes("UTF-8"));
       /* //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");*/
        cipher.init(Cipher.DECRYPT_MODE, privateKey1);//解密模式
        byte[] b = cipher.doFinal(bytes);
        String plaintext = new String(b);
        System.out.println("RAS解密后密文：" + plaintext);
        return plaintext;
    }

    /**
     * @描述： 测试RSA加密 解密类，打印对应信息
     * @参数： [args]
     * @返回值： void
     * @创建人： zhangww
     * @创建时间： 2019-03-05
     * @修改人和其它信息：
     */
    public static void main(String[] args) throws Exception {
        //测试通过RSA公钥加密解密全过程（公钥私钥成对出现，每次都是不一样的）
        Cipher cipher = Cipher.getInstance("RSA");
        NewRSA newRSA = new NewRSA();
        Map<String, String> map = newRSA.getRasKey();
        String publicKey = map.get("publicKey");
        String privateKey = map.get("privateKey");
        String miwen = newRSA.encryption("123654", publicKey, cipher);
        newRSA.decrypt(miwen, privateKey, cipher);
    }
}
