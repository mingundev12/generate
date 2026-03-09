package com.generate.service;

import com.generate.dto.GeneratorDto;
import com.generate.dto.SourceDto;
import com.generate.entity.GenerateSource;
import com.generate.entity.Generator;
import com.generate.repository.GenerateSourceRepo;
import com.generate.repository.GeneratorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneratorService {
    private final GeneratorRepo generatorRepo;
    private final GenerateSourceRepo generateSourceRepo;

    public List<SourceDto> getSourceList(long id) {
        List<GenerateSource> sources = generateSourceRepo.findByGeneratorId(id);
        List<SourceDto> sourceList = new ArrayList<>();

        for(GenerateSource source : sources) {
            sourceList.add(new SourceDto(source));
        }

        return sourceList;
    }

    public List<GeneratorDto> getGeneratorList() {
        List<Generator> generators = generatorRepo.findAll();

        List<GeneratorDto> generatorDtos = new ArrayList<>();
        for (Generator generator : generators) {
            GeneratorDto generatorDto = new GeneratorDto(generator);
            generatorDtos.add(generatorDto);
        }
        return generatorDtos;
    }

    public String getGeneratorName(long id) {
        Generator generator = generatorRepo.findById(id);
        return generator.getGeneratorName();
    }
}
