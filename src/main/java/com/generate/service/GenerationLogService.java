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

        if (end.isAfter(LocalDateTime.now())) {
            end = LocalDateTime.now()
                    .withMinute(0).withSecond(0).withNano(0).plusHours(1);
        }
        while (current.isBefore(end)) {
            GenerationLog generationLog = new GenerationLog();

            generationLog.setSourceId(sourceId);
            generationLog.setEfficiency(SolarDataUtil.getEfficiency());
            generationLog.setCreatedAt(current);
            generationLog.setGenerationKwh
                    (SolarDataUtil.generatePower(
                            current, maxCapacity, generationLog.getEfficiency()));
            if(generationLog.getGenerationKwh() == 0.0) {
                generationLog.setEfficiency(1.0);
            }
            if(generationLog.getGenerationKwh() >= 0){
                generateList.add(generationLog);
            }
            current = current.plusHours(1);
        }

        if (!generateList.isEmpty()) {
            generationLogRepo.saveAll(generateList);
        }

    }

    public List<DailyLogDto> getLogList(long id, LocalDate date) {
        List<GenerationLog> generationLogs =
                generationLogRepo.findDailyLogs
                        (id, date.atStartOfDay(), date.atStartOfDay().plusDays(1));
        List<DailyLogDto> dailyLogDtoList = new ArrayList<>();

        if(generationLogs.isEmpty()) {
            GenerateSource source = generateSourceService.getGenerateSource(id);
            GenerationLog generationLog = generationLogRepo.findLastLog(id);

            LocalDateTime start = generationLog == null ?
                    source.getCreatedAt().withMinute(0).withSecond(0).withNano(0) :
                    generationLog.getCreatedAt().withMinute(0).withSecond(0).withNano(0)
                            .plusHours(1);
            LocalDateTime end = LocalDateTime.now();

            generateEnergy(source.getSourceId(), source.getMaxCapacity(), start, end);
            generationLogs =
                    generationLogRepo.findDailyLogs
                            (id, date.atStartOfDay(), date.atStartOfDay().plusDays(1));
        }
        for(GenerationLog generationLog : generationLogs) {
            dailyLogDtoList.add(new DailyLogDto(generationLog));
        }

        return dailyLogDtoList;
    }
}
