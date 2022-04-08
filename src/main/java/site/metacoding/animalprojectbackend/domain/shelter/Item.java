package site.metacoding.animalprojectbackend.domain.shelter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    private String careRegNo; // 보호소코드
    private String careNm; // 보호소명
}
