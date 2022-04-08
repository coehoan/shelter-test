package site.metacoding.animalprojectbackend.web.animalapi;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.animalprojectbackend.domain.shelter.ShelterDto;
import site.metacoding.animalprojectbackend.domain.sigungu.SigunguDto;
import site.metacoding.animalprojectbackend.service.api.TestApiService;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestApiService testApiService;

    @GetMapping("/sigungu/shelter")
    public String shelter(SigunguDto sigunguDto, Model model) {

        List<SigunguDto> sigunguEntity = testApiService.보호소다운로드(sigunguDto);

        model.addAttribute("choongbooklist", sigunguEntity);

        return "/api/sigunguDownload";
    }
}
