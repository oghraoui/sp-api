package com.sbg.spapi.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GraphClient {

    @Value("${sp.client.id}") private String clientId;
    @Value("${sp.client.secret}") private String clientSecret;
    @Value("${sp.tenant}") private String tenant;
    final String[] scopes = new String[] { "https://graph.microsoft.com/.default" };

    private GraphServiceClient graphServiceClient;

    public GraphClient() {}

    private void initGraph() throws Exception {
        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenant)
                .build();

        if (null == credential) {
            throw new Exception("Unexpected graph authentication error.");
        }

        this.graphServiceClient = new GraphServiceClient(credential, scopes);
        log.info("MS Graph Client initialized.");
    }

    @Bean
    public GraphServiceClient getGraphServiceClient() {
        if (this.graphServiceClient == null) {
            try {
                initGraph();
            } catch (Exception e) {
                throw new RuntimeException("Error initializing Graph Service", e);
            }
        }

        return this.graphServiceClient;
    }
}
