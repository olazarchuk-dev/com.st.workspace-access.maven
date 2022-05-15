//package com.vinsguru.reactivemongo.service;
//
//import com.vinsguru.reactivemongo.entity.WorkspaceStage;
//import com.vinsguru.reactivemongo.repository.WorkspaceStageRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class WorkspaceStageServiceTest {
//
//    @InjectMocks
//    private WorkspaceStageService service; // TODO: класс (бин) должен быть протестирован
//
//    @Mock
//    private WorkspaceStageRepository repository; // TODO: класс(ы) создаются в контексте тестируемого класса (здесь просто ожидаемый результат...)
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
//        // Mock: response Repository data
//        String expectedRequest = "6278f41134553518b7f978f4";
//        when( repository.findById(expectedRequest) )
//                .thenReturn( expectedResponse );
//
//        //  THEN
//        // Test: Expected response Service data
//        StepVerifier.create( service.getPerson(expectedRequest) )
//                .assertNext(person -> assertThat(person)
//                        .isNotNull()
//                        .hasFieldOrPropertyWithValue("id", "6278f41134553518b7f978f4")
//                        .hasFieldOrPropertyWithValue("name", "smith")
//                        .hasFieldOrPropertyWithValue("age", 32)
//                        .hasFieldOrPropertyWithValue("skills", Arrays.asList("qa", "selenium")))
//                .verifyComplete();
//    }
//}
