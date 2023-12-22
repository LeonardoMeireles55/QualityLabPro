package leonardo.labutilities.qualitylabpro.services;

import leonardo.labutilities.qualitylabpro.components.GenericValidatorComponent;
import leonardo.labutilities.qualitylabpro.infra.exception.ErrorHandling;
import leonardo.labutilities.qualitylabpro.domain.entitys.GenericAnalytics;
import leonardo.labutilities.qualitylabpro.records.genericAnalytics.ValuesOfLevelsGenericDTO;
import leonardo.labutilities.qualitylabpro.repositories.GenericAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenericAnalyticsService {
    private final GenericAnalyticsRepository genericAnalyticsRepository;
    private final GenericValidatorComponent genericValidatorComponent;

    public Stream<GenericAnalytics> sendValues(List<ValuesOfLevelsGenericDTO> valuesOfLevelsList) {
        var valuesFilter = valuesOfLevelsList.stream()
                .filter(values -> !genericAnalyticsRepository.existsByDateAndLevelAndName(values.date(), values.level(),
                        values.name()));
        return valuesFilter.map(values -> {
            GenericAnalytics genericAnalytics = new GenericAnalytics(values, genericValidatorComponent);
            return genericAnalyticsRepository.save(genericAnalytics);
        });
    }

    @Cacheable(value = "name")
    public Page<ValuesOfLevelsGenericDTO> getAllResults(Pageable pageable) {
        log.info("Retrieve all results...");
        return genericAnalyticsRepository.findAll(pageable).map(ValuesOfLevelsGenericDTO::new);
    }
    @Cacheable(value = "name")
    public Stream<ValuesOfLevelsGenericDTO> getResultsByName(Pageable pageable, String name) {
        var nameUpper = name.toUpperCase();
        if (!genericAnalyticsRepository.existsByName(nameUpper)) {
            throw new ErrorHandling.ResourceNotFoundException("Results not found.");
        }
        log.info("Retrieve results by name...");
        return genericAnalyticsRepository.findAllByName(pageable, nameUpper).stream()
                .map(ValuesOfLevelsGenericDTO::new);
    }
    @Cacheable(value ={"name", "level"})
    public Stream<ValuesOfLevelsGenericDTO> getResultsByNameAndLevel(Pageable pageable, String name, String level) {
        var nameUpper = name.toUpperCase();
        if (!genericAnalyticsRepository.existsByName(nameUpper)) {
            throw new ErrorHandling.ResourceNotFoundException("Results not found.");
        }
        if (Objects.equals(level, "1")) {
            level = "PCCC1";
        } else if (Objects.equals(level, "2")) {
            level = "PCCC2";
        } else {
            throw new ErrorHandling.ResourceNotFoundException("Level not found.");
        }
        log.info("Retrieve results by name and level...");
        return genericAnalyticsRepository.findAllByNameAndLevel(pageable, nameUpper, level).stream()
                .map(ValuesOfLevelsGenericDTO::new);
    }
    @Cacheable(value = "name")
    public Stream<ValuesOfLevelsGenericDTO> getResultsByDateAsc(String name) {
        var nameUpper = name.toUpperCase();
        if (!genericAnalyticsRepository.existsByName(nameUpper)) {
            throw new ErrorHandling.ResourceNotFoundException("Results not found.");
        }
        log.info("Retrieve results by dateAsc...");
        return genericAnalyticsRepository.findAllByNameOrderByDateAsc(nameUpper).stream()
                .map(ValuesOfLevelsGenericDTO::new);
    }
    @Cacheable(value = "name")
    public Stream<ValuesOfLevelsGenericDTO> getResultsByDateDesc(String name) {
        var nameUpper = name.toUpperCase();
        if (!genericAnalyticsRepository.existsByName(nameUpper)) {
            throw new ErrorHandling.ResourceNotFoundException("Results not found.");
        }
        log.info("Retrieve results by dateDesc...");
        return genericAnalyticsRepository.findAllByNameOrderByDateDesc(nameUpper).stream()
                .map(ValuesOfLevelsGenericDTO::new);
    }
}