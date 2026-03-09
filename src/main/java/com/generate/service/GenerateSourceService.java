package com.generate.service;

import com.generate.dto.SourceDto;
import com.generate.entity.GenerateSource;
import com.generate.repository.GenerateSourceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerateSourceService {
    private final GenerateSourceRepo generateSourceRepo;

    public List<SourceDto> getSourceList(long id) {
        List<GenerateSource> sources = generateSourceRepo.findByGeneratorId(id);
        List<SourceDto> sourceList = new ArrayList<>();

        for(GenerateSource source : sources) {
            sourceList.add(new SourceDto(source));
        }

        return sourceList;
    }

    public LocalDate getAddedDate(long id) {
        GenerateSource generateSource = getGenerateSource(id);

        return generateSource.getCreatedAt().toLocalDate();
    }

    public GenerateSource getGenerateSource(long id) {
        return generateSourceRepo.findById(id);
    }
}
