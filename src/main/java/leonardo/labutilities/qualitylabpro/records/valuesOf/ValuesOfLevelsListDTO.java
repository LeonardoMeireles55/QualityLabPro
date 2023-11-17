package leonardo.labutilities.qualitylabpro.records.valuesOf;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import leonardo.labutilities.qualitylabpro.main.Analytics;

public record ValuesOfLevelsListDTO(
        @NotBlank @Pattern(regexp = "^[^0-9]+$", message = "test name must contain only letters.")
        String name,
        String date,
        @Digits(integer=6, fraction=2)
        Double normalValue,
        @Digits(integer=6, fraction=2)
        Double highValue,
        String normalValid, String highValid, String normalObs, String highObs) {
    public ValuesOfLevelsListDTO(Analytics analytics) {
        this(analytics.getName(), analytics.getData(), analytics.getNormalValue(), analytics.getHighValue(), analytics.getNormalValid(), analytics.getHighValid(), analytics.getNormalObs(), analytics.getHighObs());
    }
}