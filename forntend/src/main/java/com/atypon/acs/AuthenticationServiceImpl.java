package com.atypon.acs;

import com.atypon.consensus.SignatureComputer;
import com.atypon.domain.AuthorizedInquirer;
import com.atypon.domain.HasAccessQuery;
import com.atypon.domain.UserRequest;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Gson GSON = new Gson();


    private final AuthorizedInquirer authorizedInquirer;
    private final String authenticationServiceProvider;
    private final SignatureComputer<HasAccessQuery> signatureComputer;

    public AuthenticationServiceImpl(String authenticationServiceProvider,
                                     SignatureComputer<HasAccessQuery> signatureComputer,
                                     AuthorizedInquirer authorizedInquirer) {
        this.authenticationServiceProvider = authenticationServiceProvider;
        this.signatureComputer = signatureComputer;
        this.authorizedInquirer = authorizedInquirer;
    }

    @Override
    public boolean hasAccess(UserRequest userRequest, String username, String contentId) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(authenticationServiceProvider);
            HasAccessQuery hasAccessQuery = createHasAccessQuery(userRequest, username, contentId);
            StringEntity requestBody = new StringEntity(GSON.toJson(hasAccessQuery));
            request.addHeader("content-type", "application/json");
            request.setEntity(requestBody);
            HttpResponse response = httpClient.execute(request);
            String stringResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            return Boolean.parseBoolean(stringResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private HasAccessQuery createHasAccessQuery(UserRequest userRequest, String username, String contentId) {
        HasAccessQuery hasAccessQuery = new HasAccessQuery();
        hasAccessQuery.setUserRequest(userRequest);
        hasAccessQuery.setUsername(username);
        hasAccessQuery.setContentId(contentId);
        hasAccessQuery.setInquirerName(authorizedInquirer.getName());
        String signature = signatureComputer.sign(hasAccessQuery, authorizedInquirer.getPrivateKey());
        hasAccessQuery.setInquirerSignature(signature);
        return hasAccessQuery;
    }

}
