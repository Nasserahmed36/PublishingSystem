package com.atypon.consensus;

/**
 * A simple key-pair class.
 *
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/09/04
 */
public class Keys {
    public String publicKey;
    public String privateKey;

    /**
     * Creates an instance of the {@link Keys} class.
     */
    public Keys() {
    }

    /**
     * Creates an instance of the {@link Keys} class.
     *
     * @param publicKey  the public key of this key pair object
     * @param privateKey the private key of this key pair object.
     */
    public Keys(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
