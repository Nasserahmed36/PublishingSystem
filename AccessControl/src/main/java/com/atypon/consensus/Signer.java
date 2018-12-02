package com.atypon.consensus;

import java.security.InvalidKeyException;


/**
 * A simple signature computer and verifier.
 *
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/09/04
 */
public interface Signer {
    /**
     * Signs the specified message with the specified private key
     * and returns the resulted signature.
     *
     * @param message    the message to be signed.
     * @param privateKey the key to sign the message with.
     * @return the resulted signature of singing specified message
     * with the specified key.
     */
    String sign(String message, String privateKey) throws InvalidKeyException;

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
    boolean verify(String message, String signature, String publicKey)
            throws InvalidKeyException;

    /**
     * Generates a key pair.
     *
     * @return the generated key pair.
     */
    Keys generateKeyPair();


}
