//package com.vinsguru.reactivemongo.controller;
//
//import com.vinsguru.reactivemongo.entity.WorkspaceStage;
//import com.vinsguru.reactivemongo.repository.WorkspaceStageRepository;
//import com.vinsguru.reactivemongo.service.WorkspaceStageService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebFluxTest
//public class WorkspaceStageControllerTest {
//
//    @Autowired
//    private WebTestClient client; // TODO: класс (бин) должен быть протестирован
//
//    @MockBean
//    private WorkspaceStageService service; // TODO: класс(ы) создаются в контексте тестируемого класса (здесь просто ожидаемый результат...)
//
//    @MockBean
//    private WorkspaceStageRepository repository;
//
//    @Test
//    void findByIdTest() {
//        //  GIVEN
//        // Init: Set response data
//        var response = WorkspaceStage.builder()
//                .id("6278f41134553518b7f978f4")
//                .name("smith")
//                .age(32)
//                .skills(Arrays.asList("qa", "selenium"))
//                .build();
//
//        Mono<WorkspaceStage> expectedResponse = Mono.just(response);
//
//        //  WHEN
//        String expectedRequest = "6278f41134553518b7f978f4";
//        when( service.getPerson(expectedRequest) )
//                .thenReturn( expectedResponse );
//
//        //  THEN
//        client.get().uri("/person/{id}", expectedRequest)
//                .exchange()
//                .expectBody(WorkspaceStage.class)
//                .value(person -> assertThat(person)
//                        .isNotNull()
//                        .hasFieldOrPropertyWithValue("id", "6278f41134553518b7f978f4")
//                        .hasFieldOrPropertyWithValue("name", "smith")
//                        .hasFieldOrPropertyWithValue("age", 32)
//                        .hasFieldOrPropertyWithValue("skills", Arrays.asList("qa", "selenium")));
//    }
//}
