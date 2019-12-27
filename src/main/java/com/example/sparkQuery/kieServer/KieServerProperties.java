package com.example.sparkQuery.kieServer;

import org.kie.api.KieServices;
import org.kie.server.client.*;
import org.kie.server.client.credentials.EnteredCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KieServerProperties {

    @Value("${kieserver.url}")
    private String serverUrl;

    @Value("${kieserver.user}")
    private String user;

    @Value("${kieserver.password}")
    private String userCredential;

    @Value("${kieserver.container}")
    private String container;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(String userCredential) {
        this.userCredential = userCredential;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public CredentialsProvider credentialsProvider(){
        return new EnteredCredentialsProvider(user, userCredential);
    }

    @Bean
    @Autowired
    public KieServicesConfiguration kieServicesConfig(CredentialsProvider credentialsProvider) {
        return KieServicesFactory.newRestConfiguration(serverUrl, credentialsProvider);
    }

    @Bean
    @Autowired
    public KieServicesClient kieServicesClient(KieServicesConfiguration kieServicesConfig) {
        return KieServicesFactory.newKieServicesClient(kieServicesConfig);
    }

    @Bean
    @Autowired
    public RuleServicesClient rulesClient(KieServicesClient kieServicesClient) {
        return  kieServicesClient.getServicesClient(RuleServicesClient.class);
    }
}
