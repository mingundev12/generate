package com.generate.service;

import com.generate.dto.DailyLogDto;
import com.generate.entity.GenerateSource;
import com.generate.entity.GenerationLog;
import com.generate.repository.GenerationLogRepo;
import com.generate.util.SolarDataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerationLogService {
    private final GenerationLogRepo generationLogRepo;
    private final GenerateSourceService generateSourceService;

    public void generateEnergy
            (long sourceId, double maxCapacity,
             LocalDateTime start, LocalDateTime end) {
        List<GenerationLog> generateList = new ArrayList<>();

        LocalDateTime current = start;
        while (current.isBefore(end)) {
            GenerationLog generationLog = new GenerationLog();

            generationLog.setSourceId(sourceId);
            generationLog.setEfficiency(SolarDataUtil.getEfficiency());
            generationLog.setGenerationKwh
                    (SolarDataUtil.generatePower(
                            current, maxCapacity, generationLog.getEfficiency()));
            generateList.add(generationLog);
            current = current.plusHours(1);
        }

        if (!generateList.isEmpty()) {
            generationLogRepo.save(generateList);
        }
    }

    public List<DailyLogDto> getLogList(long id, LocalDate date) {
        GenerateSource generateSource = generateSourceService.getGenerateSource(id);
        
        return null;
    }
}
