package com.example.vue.common.annotation;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author tengfei
 */
@Component
@Slf4j
public class SaveEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(), (Field field) -> {
                ReflectionUtils.makeAccessible(field);
                // 如果字段添加了我们自定义的AutoValue注解
                if (field.isAnnotationPresent(AutoIncKey.class) && field.get(source) instanceof Number && field.getLong(source) == 0) {
                    // field.get(source) instanceof Number &&
                    // field.getLong(source)==0
                    // 判断注解的字段是否为number类型且值是否等于0.如果大于0说明有ID不需要生成ID
                    // 设置自增ID
                    field.set(source, getNextId(source.getClass().getSimpleName()));
                    log.debug("集合的ID为=======================" + source);
                }
            });
        }
    }

    private Long getNextId(String collName) {
        log.debug("CollectionsName=======================" + collName);
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SeqInfo seq = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
        if (seq != null) {
            log.debug(collName + "集合的ID为=======================" + seq.getSeqId());
            return seq.getSeqId();
        }
        return 0L;
    }

    @ToString
    @Document(collection = "sequence")
    @Data
    private static class SeqInfo {
        @Id
        private String id;

        @org.springframework.data.mongodb.core.mapping.Field
        private String collName;

        @org.springframework.data.mongodb.core.mapping.Field
        private Long seqId;
    }
}
