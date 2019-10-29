package com.example.vue.biz.log.repository;

import com.example.vue.biz.log.domain.OperationLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author tengfei
 */
public interface OperationLogRepository extends MongoRepository<OperationLog, String>, LogRepositoryCustom {

    @Query(value = "{type: {$in: ?0}}")
    List<OperationLog> getAllByType(Integer[] type);
}
