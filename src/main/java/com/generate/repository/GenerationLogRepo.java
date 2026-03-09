package com.generate.repository;

import com.generate.entity.GenerationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenerationLogRepo {
    int save(List<GenerationLog> generateList);
}
