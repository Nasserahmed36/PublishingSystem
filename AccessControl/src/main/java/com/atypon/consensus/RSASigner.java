package com.atypon.consensus;


import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

import static com.atypon.consensus.AsymmetricEncrypting.RSA;

/**
 * A simple implementation of {@link Signer} which designed to
 * compute and verify the signature based on the RSA algorithm.
 *
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/09/04
 */
public class RSASigner implements Signer {

    private final int keySize;

    /**
     * Creates an instance of {@link RSASigner} class.
     *
     * @param keySize the size of the key.
     */
    public RSASigner(int keySize) {
        this.keySize = keySize;
    }

    /**
     * Signs the specified message with the specified private key
     * and returns the resulted signature.
     *
     * @param message    the message to be signed.
     * @param privateKey the key to sign the message with.
     * @return the resulted signature of singing specified message
     * with the specified key.
     */
    @Override
    public String sign(String message, String privateKey) throws InvalidKeyException {
        Objects.requireNonNull(message);
        Objects.requireNonNull(privateKey);
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
            return Base64.getEncoder().encodeToString(RSA.sign(message.getBytes(), privKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns true if the specified signature is
     * correct with respect to the specified message and key.
     *
     * @param message   the message whose signature to be verified.
     * @param signature the signature of message.
     * @param publicKey the public key corresponding to the private key
     *                  which the message was signed with.
     * @return true if the specified signature is correct with respect
     * to the specified message and key.
     */
    @Override
    public boolean verify(String message, String signature, String publicKey) throws InvalidKeyException {
        Objects.requireNonNull(message);
        Objects.requireNonNull(signature);
        Objects.requireNonNull(publicKey);
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(publicKeyBytes);
            RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(pubSpec);
            return RSA.verify(message.getBytes(), Base64.getDecoder().decode(signature), pubKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a key pair.
     *
     * @return the generated key pair.
     */
    @Override
    public Keys generateKeyPair() {
        java.security.KeyPair keyPair = RSA.generateKeyPair(keySize);
        Keys keys = new Keys();
        keys.publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        keys.privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        return keys;
    }
}
