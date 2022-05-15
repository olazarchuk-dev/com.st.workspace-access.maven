//package com.vinsguru.reactivemongo.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.cloudyrock.mongock.ChangeLog;
//import com.github.cloudyrock.mongock.ChangeSet;
//import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
//import com.vinsguru.reactivemongo.entity.WorkspaceStage;
//import com.vinsguru.reactivemongo.error.ParseNameException;
//import com.vinsguru.reactivemongo.service.mapper.WorkspaceStageMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static com.vinsguru.reactivemongo.utils.GzipUtil.doHandleDecompressPayload;
//import static org.springframework.data.mongodb.core.query.Criteria.where;
//
//@ChangeLog
//@RequiredArgsConstructor
//@Slf4j
//public class WorkspaceStageSyncChangeLog {
//    private final WorkspaceStageMapper workspaceStageMapper;
//    private final ObjectMapper objectMapper;
//
//    private final AtomicInteger successfulUpdatesCounter = new AtomicInteger();
//
//    @Value("${app.sync.one-per-query-limit}")
//    public int onePerQueryLimit;
//
//    @Value("${app.sync.update-expiration-seconds}")
//    public int updateExpirationSeconds = 30;
//
//    @ChangeSet(order = "001", id = "setFirstAndLastNameToUsers", author = "olazarchuk-dev", runAlways = true)
//    public void syncPayloadDecodeFromStage(MongockTemplate mongockTemplate) {
//        log.info("Order-ChangeSet | Start Users-Sync to Database");
//
//        Criteria toDate = Criteria.where("updatedAt").lt( Instant.now().minusSeconds(updateExpirationSeconds) );
//        var query = Query.query(
//                new Criteria().andOperator(toDate));
//
//        query.fields().include("_id", "payload");
//
//        mongockTemplate.count(query, WorkspaceStage.class);
//        query.limit(onePerQueryLimit);
//
//        List<WorkspaceStage> stages = mongockTemplate.find(query, WorkspaceStage.class);
//        while (!stages.isEmpty()) {
//            stages.stream()
//                    .map(workspaceStageMapper::toDto)
//                    .forEach(stage -> {
//                try {
//                    var criteria = where("_id").is(stage.getId());
//                    var names = splitNamesForUser(stage);
//
//                    stage.getPayload()
//
//                    var firstName = names[0];
//                    Update update = new Update()
//                            .set("payloadDecode", firstName)
//                            .set("updatedAt", Instant.now());
//                    mongockTemplate.findAndModify(new Query(criteria), update, WorkspaceStage.class);
//                    successfulUpdatesCounter.getAndIncrement();
//                } catch (Exception | ParseNameException ex) {
//                    ex.getStackTrace();
//                }
//            });
//
//            stages = mongockTemplate.find(query, WorkspaceStage.class);
//        }
//
//        log.info("Order-ChangeSet | Successful count update = {} user(s)", successfulUpdatesCounter);
//        log.info("Order-ChangeSet | Finish Users-Sync to Database");
//    }
//
//    private String[] splitNamesForUser(WorkspaceStage user) throws ParseNameException {
//        doHandleDecompressPayload(user.getPayload());
//
//        if (StringUtils.isEmpty(user.getFullName()) || !user.getFullName().contains(" ")) {
//            throw new ParseNameException("Failed to parse the user's name");
//        }
//        return user.getFullName().split(" ");
//    }
//}
