package leonardo.labutilities.qualitylabpro.services;

import leonardo.labutilities.qualitylabpro.infra.exception.ErrorHandling;
import leonardo.labutilities.qualitylabpro.main.entitys.Analytics;
import leonardo.labutilities.qualitylabpro.records.valuesOfAnalytics.ValuesOfLevelsDTO;
import leonardo.labutilities.qualitylabpro.records.valuesOfAnalytics.ValuesOfLevelsListDTO;
import leonardo.labutilities.qualitylabpro.repositories.AnalyticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AnalyticsService {

    private final AnalyticRepository analyticRepository;
    private final AnalyticsValidatorService analyticsValidatorService;

    public Analytics sendValues(ValuesOfLevelsDTO values) {
        return analyticRepository.save(new Analytics(values, analyticsValidatorService));
    }

    public List<Analytics> sendValuesList(List<ValuesOfLevelsDTO> valuesOfLevelsDTOList) {
        List<Analytics> analyticsList = new ArrayList<>();
        for (ValuesOfLevelsDTO values : valuesOfLevelsDTOList) {
            var analyticsLevels = new Analytics(values, analyticsValidatorService);
            analyticRepository.save(analyticsLevels);
            analyticsList.add(analyticsLevels);
        }
        return analyticsList;
    }

    public void deleteValues(Long id) {
        if (!analyticRepository.existsById(id)) {
            throw new ErrorHandling.ResourceNotFoundException("values by id not found");
        }
        analyticRepository.deleteById(id);
    }

    public void deleteValuesAll() {
        if(analyticRepository.findAll().isEmpty()) {
            throw new ErrorHandling.ResourceNotFoundException("already empty");
        }
        analyticRepository.deleteAll();
    }

    public List<ValuesOfLevelsListDTO> getResultsAll(Pageable pageable) {
        if (!analyticRepository.findAll().isEmpty()) {
            return analyticRepository.findAll(pageable).stream().map(ValuesOfLevelsListDTO::new).toList();
        }
        throw new ErrorHandling.ResourceNotFoundException("Results is empty");
    }
    @Cacheable(value = "name")
    public List<ValuesOfLevelsListDTO> getResultsByName(Pageable pageable, String name) {
        var nameUpper = name.toUpperCase();
        if (!analyticRepository.existsByName(nameUpper)) {
            throw new ErrorHandling.ResourceNotFoundException("Results by name not found");        }
        return analyticRepository.findAllByName(pageable, nameUpper).stream()
                .map(ValuesOfLevelsListDTO::new).toList();
    }
}
