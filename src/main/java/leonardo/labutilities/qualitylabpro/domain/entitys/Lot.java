package leonardo.labutilities.qualitylabpro.domain.entitys;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import leonardo.labutilities.qualitylabpro.record.lot.ValueOfLotRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "lot")
@NoArgsConstructor
public class Lot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lot;

    public Lot(ValueOfLotRecord valueOfLotRecord) {
        this.lot = valueOfLotRecord.valueLot();
    }
}
