package leonardo.labutilities.qualitylabpro.record.defaultValues;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import leonardo.labutilities.qualitylabpro.domain.entitys.DefaultValues;

public record DefaultRegisterListRecord(
         @NotBlank @Pattern(regexp = "^[^0-9]+$", message = "test name must contain only letters.")
         String name,
//         @Digits(integer=6, fraction=2)
         Double normalSd,
         @Digits(integer=6, fraction=2)
         Double normalMean,
         @Digits(integer=6, fraction=2)
         Double highSd,
         @Digits(integer=6, fraction=2)
         Double highMean,
         Double normalMaxValue) {
    public DefaultRegisterListRecord(DefaultValues defaultValues) {
        this(defaultValues.getName(), defaultValues.getNormalSd(),
                defaultValues.getNormalMean(), defaultValues.getHighSd(),
                defaultValues.getHighMean(), defaultValues.getNormalMaxValue());
    }
}
