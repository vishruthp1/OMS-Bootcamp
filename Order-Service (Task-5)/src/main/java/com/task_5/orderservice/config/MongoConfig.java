//package com.task_5.orderservice.config;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Configuration
//@EnableMongoRepositories(basePackages = "com.task_5.orderservice.repository")
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Value("${spring.data.mongodb.database}")
//    private String database;
//
//    @Value("${spring.data.mongodb.host}")
//    private String host;
//
//    @Value("${spring.data.mongodb.port}")
//    private int port;
//
//    @Value("${spring.data.mongodb.username}")
//    private String username;
//
//    @Value("${spring.data.mongodb.password}")
//    private String password;
//
//    @Value("${spring.data.mongodb.authentication-database}")
//    private String authDatabase;
//
//    @Override
//    protected String getDatabaseName() {
//        return database;
//    }
//
//    @Override
//    protected Collection<String> getMappingBasePackages() {
//        return Collections.singleton("com.task_5.orderservice");
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        MongoCredential credential = MongoCredential.createCredential(username, authDatabase, password.toCharArray());
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .credential(credential)
//                .applyToClusterSettings(builder ->
//                    builder.hosts(Collections.singletonList(new ServerAddress(host, port))))
//                .build();
//
//        return MongoClients.create(settings);
//    }
//}
