package com.generate.repository;

import com.generate.entity.GenerationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GenerationLogRepo {
    int saveAll(List<GenerationLog> generateList);

    List<GenerationLog> findDailyLogs(
            @Param("sourceId") long sourceId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    GenerationLog findLastLog(long sourceId);
}
