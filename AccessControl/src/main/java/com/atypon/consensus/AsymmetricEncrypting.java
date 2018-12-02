package com.atypon.consensus;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * a package-private class for internal use.
 *
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/09/04
 */

class AsymmetricEncrypting {
    private static byte[] encrypt(byte[] message, KeySpec keySpec, String algorithmName)
            throws InvalidKeyException {
        try {
            PublicKey publicKey = KeyFactory.getInstance(algorithmName).generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(message);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
                | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] decrypt(byte[] message, KeySpec keySpec, String algorithmName) throws InvalidKeyException {
        try {
            PrivateKey privateKey = KeyFactory.getInstance(algorithmName).generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(message);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
                | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static class RSA {

        private static final String ALGORITHM_RSA = "RSA";
        private static final String ALGORITHM_SIGNATURE_SHA256 = "SHA256withRSA";

        public static byte[] encrypt(byte[] message, byte[] key) throws InvalidKeyException {
            Objects.requireNonNull(message);
            Objects.requireNonNull(key);
            return AsymmetricEncrypting.encrypt(message, new X509EncodedKeySpec(key), ALGORITHM_RSA);
        }

        public static byte[] decrypt(byte[] message, byte[] key) throws InvalidKeyException {
            Objects.requireNonNull(message);
            Objects.requireNonNull(key);
            return AsymmetricEncrypting.decrypt(message, new PKCS8EncodedKeySpec(key), ALGORITHM_RSA);
        }

        static byte[] sign(byte[] message, PrivateKey privateKey) throws InvalidKeyException {
            Objects.requireNonNull(message);
            Objects.requireNonNull(privateKey);
            Signature privateSignature = null;
            try {
                privateSignature = Signature.getInstance(ALGORITHM_SIGNATURE_SHA256);
                privateSignature.initSign(privateKey);
                privateSignature.update(message);
                return privateSignature.sign();
            } catch (NoSuchAlgorithmException | SignatureException e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean verify(byte[] message, byte[] signature, PublicKey publicKey) throws InvalidKeyException {
            try {
                Objects.requireNonNull(message);
                Objects.requireNonNull(signature);
                Objects.requireNonNull(publicKey);
                Signature publicSignature = Signature.getInstance(ALGORITHM_SIGNATURE_SHA256);
                publicSignature.initVerify(publicKey);
                publicSignature.update(message);
                return publicSignature.verify(signature);
            } catch (SignatureException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        static KeyPair generateKeyPair(int keySize) {
            try {
                KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
                generator.initialize(keySize);
                return generator.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


