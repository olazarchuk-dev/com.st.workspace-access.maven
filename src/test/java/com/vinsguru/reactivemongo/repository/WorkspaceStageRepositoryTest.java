//package com.vinsguru.reactivemongo.repository;
//
//import com.vinsguru.reactivemongo.utils.JsonArrayUtil;
//import com.vinsguru.reactivemongo.utils.ResourceUtil;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.core.io.Resource;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.io.IOException;
//import java.time.Duration;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Testcontainers
//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class) // TODO: exclude AutoConfiguration...
//public class WorkspaceStageRepositoryTest {
//
//    private String jsonFreelancers;
//
//    @Container
//    static MongoDBContainer container = new MongoDBContainer("mongo:4.4.2");
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
//    }
//
//    @Value("classpath:data/freelancers.json")
//    private Resource freelancers;
//
//    @Autowired
//    private ReactiveMongoTemplate mongoTemplate;
//
//    @Autowired
//    private WorkspaceStageRepository workspaceStageRepository;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        jsonFreelancers = ResourceUtil.read(freelancers);
//    }
//
//    @AfterEach
//    void cleanUp() {
//
//    }
//
//    @Test
//    @DisplayName(value = "Should create and return new account")
//    void shouldCreateAndReturnNewAccount() {
//        //  GIVEN
//        // Prepare: insert test documents to MongoDB collection
//        var document = JsonArrayUtil.parse(jsonFreelancers).toDocuments();
//        mongoTemplate.insert(document, "freelancers");
//        System.out.println(jsonFreelancers);
//
//        //  WHEN
//        var collectionExists = mongoTemplate.collectionExists("freelancers")
//                .block(Duration.ofSeconds(30));
//
//        //  THEN
//        // Expected exists new collection is true
//        assertThat(collectionExists).isTrue();
//
//        String id = "62751dab37455e17fe38c34b";
//        var monoFreelancer = workspaceStageRepository.findById(id)
//                .block(Duration.ofSeconds(30));
//        System.out.println(monoFreelancer);
//    }
//}
