package site.metacoding.animalprojectbackend.service.api;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import site.metacoding.animalprojectbackend.domain.shelter.Item;
import site.metacoding.animalprojectbackend.domain.shelter.ShelterDto;
import site.metacoding.animalprojectbackend.domain.shelter.ShelterRepository;
import site.metacoding.animalprojectbackend.domain.shelter.ShelterResponseDto;
import site.metacoding.animalprojectbackend.domain.sigungu.SigunguDto;
import site.metacoding.animalprojectbackend.domain.sigungu.SigunguRepository;

@RequiredArgsConstructor
@Service
public class TestApiService {

    private final SigunguRepository sigunguRepository;
    private final ShelterRepository shelterRepository;

    public List<SigunguDto> 보호소다운로드(SigunguDto sigunguDto) {

        try {
            // 서비스키
            String key = "jDqHGG%2BaNG47ijh6s3XzB%2BuF8fJOeovccnw%2FZtc9wLQUaKJumPo%2Frl1a2ygZ68dv9L0PD7drvpjPAeTnnB9f%2FQ%3D%3D";

            // 시도 코드에 따른 변수들
            Integer busan = 6270000;

            List<SigunguDto> mSigunguDto = sigunguRepository.findShelter(busan);

            for (SigunguDto mSigunguList : mSigunguDto) {
                System.out.println(mSigunguList.getOrgCd() + " : " + mSigunguList.getUprCd());

                StringBuffer urisb = new StringBuffer();
                urisb.append("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/shelter?");
                urisb.append("serviceKey=" + key);
                urisb.append("&upr_cd=");
                urisb.append(busan);
                urisb.append("&org_cd=");
                urisb.append(mSigunguList.getUprCd());
                urisb.append("&_type=JSON");

                URI uri = new URI(urisb.toString());

                RestTemplate restTemplate = new RestTemplate();

                ShelterResponseDto response = restTemplate.getForObject(uri, ShelterResponseDto.class);
                System.out.println("response 결과 : " + response);

                List<Item> shelterListDto = response.getResponse().getBody().getItems().getItem();
                System.out.println("========================" + shelterListDto.get(0).getCareRegNo() + ", "
                        + shelterListDto.get(0).getCareNm());

                List<ShelterDto> shelterDtoList = new ArrayList<ShelterDto>();

                Integer idx = 1;
                for (Item item : shelterListDto) {
                    String careRegNo, careNm;
                    Integer id = idx;
                    ShelterDto shelterDto = new ShelterDto();
                    if (!item.getCareRegNo().isEmpty() && !item.getCareRegNo().equals("") && !item.getCareNm().isEmpty()
                            && !item.getCareNm().equals("") && id != null) {
                        careRegNo = item.getCareRegNo();
                        careNm = item.getCareNm();

                        shelterDto.setId(id);
                        shelterDto.setCareRegNo(careRegNo);
                        shelterDto.setCareNm(careNm);
                        shelterDtoList.add(shelterDto);
                    }

                    idx++;
                }
                System.out.println("과연 : " + shelterDtoList);
                shelterRepository.saveAll(shelterDtoList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
