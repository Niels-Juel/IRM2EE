package ecdsaexample;
/**
 *
 * @author a.shalin
 */
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    /*
    * Генерирует сигнатуру ECDSA для строки
    */
    public String getEcdsaSign(String value) throws NoSuchAlgorithmException, 
            InvalidAlgorithmParameterException, InvalidKeyException, 
            UnsupportedEncodingException, SignatureException {
        /*
        * Создание пары ключей
        */
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */
        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(priv);
        byte[] strByte = value.getBytes("UTF-8");
        ecdsa.update(strByte);
        byte[] realSig = ecdsa.sign();
        return new BigInteger(1, realSig).toString(16);
    }
}