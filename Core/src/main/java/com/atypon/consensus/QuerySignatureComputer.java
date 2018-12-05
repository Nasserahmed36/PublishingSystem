package com.atypon.consensus;

import com.atypon.domain.HasAccessQuery;

import java.security.InvalidKeyException;

/**
 * A simple implementation of {@link SignatureComputer}.
 *
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/09/04
 */
public class QuerySignatureComputer implements
        SignatureComputer<HasAccessQuery> {
    private final Signer signer;

    public QuerySignatureComputer() {
        this.signer = new RSASigner(512);
    }


    @Override
    public String sign(HasAccessQuery hasAccessQuery, String privateKey) {
        try {
            String compressedTransaction = compress(hasAccessQuery);
            return signer.sign(compressedTransaction, privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String compress(HasAccessQuery hasAccessQuery) {
        return hasAccessQuery.getInquirerName() +
                hasAccessQuery.getContentId() +
                hasAccessQuery.getUsername() +
                hasAccessQuery.getUserRequest().getIp() +
                hasAccessQuery.getUserRequest().getUrl() +
                hasAccessQuery.getUserRequest().getMethod();
    }


    @Override
    public boolean verify(HasAccessQuery hasAccessQuery, String signature, String publicKey) {
        try {
            String compressedTransaction = compress(hasAccessQuery);
            return signer.verify(compressedTransaction, signature
                    , publicKey);
        } catch (InvalidKeyException e) {
            return false;
        }
    }
}
