package com.atypon.consensus;

/**
 * A simple signature computer and verifier.
 *
 * @param <T> the type of data whose signature to be computed.
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/09/04
 */
public interface SignatureComputer<T> {

    /**
     * Signs the specified data with the specified private key
     * and returns the resulted signature.
     *
     * @param data       the data to be signed.
     * @param privateKey the key to sign the data with.
     * @return the resulted signature of singing specified data
     * with the specified key.
     */
    String sign(T data, String privateKey);

    /**
     * Returns true if the specified signature is
     * correct with respect to the specified data and key.
     *
     * @param data      the data whose signature to be verified.
     * @param signature the signature of data.
     * @param publicKey the public key corresponding to the private key
     *                  which the data was signed with.
     * @return true if the specified signature is correct with respect
     * to the specified data and key.
     */
    boolean verify(T data, String signature, String publicKey);
}
