package com.generate.repository;

import com.generate.entity.GenerateSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenerateSourceRepo {
    List<GenerateSource> findByGeneratorId(long id);
}
