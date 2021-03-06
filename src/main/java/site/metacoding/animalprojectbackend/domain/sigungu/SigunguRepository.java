package site.metacoding.animalprojectbackend.domain.sigungu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SigunguRepository extends JpaRepository<SigunguDto, Integer> {

    @Query(value = "SELECT * FROM sigungudto WHERE orgdownNm = :orgdownNm", nativeQuery = true)
    List<SigunguDto> findShelter(@Param("orgdownNm") Integer orgdownNm);

}
