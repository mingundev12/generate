package com.generate.repository;

import com.generate.entity.Generator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GeneratorRepo {
    List<Generator> findAll();

    Generator findById(long id);
}
