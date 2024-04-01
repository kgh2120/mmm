package com.spring.mmm.domain.recommends.controller;

import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.request.XYRequest;
import com.spring.mmm.domain.recommends.controller.response.*;
import com.spring.mmm.domain.recommends.exception.RecommendErrorCode;
import com.spring.mmm.domain.recommends.exception.RecommendException;
import com.spring.mmm.domain.recommends.service.RecommendService;
import com.spring.mmm.domain.weathers.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<RecommandRandomFood> recommendRandomFood(){
        return ResponseEntity.ok(RecommandRandomFood.builder()
                .foods(recommendService.recommendRandomFood())
                .build());
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<LunchRecommendResponse> recommendLunch(
            @PathVariable Long groupId,
            @RequestBody LunchRecommendRequest lunchRecommendRequest
    ){
        return ResponseEntity.ok(LunchRecommendResponse.builder()
                .foods(recommendService.lunchRecommendFood(lunchRecommendRequest))
                .build()
        );
    }

    @GetMapping("/groups/{groupId}/new")
    public ResponseEntity<NewRecommendedFoodInformation> recommendNewFood(
            @PathVariable Long groupId
    ){
        return ResponseEntity.ok(recommendService.newRecommendFood(groupId));
    }

    @GetMapping("/weather")
    public ResponseEntity<FoodInformation> recommendWeatherFood(@RequestBody XYRequest request) {

        double latitude = request.getLatitude();
        double longitude = request.getLongitude();
        WeatherDTO weatherDTO = weatherService.getWeather(latitude, longitude);
        log.debug("weatherDTO : {}",weatherDTO);
        FoodInformation foodInformation = weatherService.getWeatherFood(weatherDTO);
        log.info("foodInformation : {}", foodInformation);
        if (foodInformation==null) throw new RecommendException(RecommendErrorCode.FOOD_RECOMMEND_NOT_FOUND);
        log.debug("foodInfo : {}",foodInformation);

        return ResponseEntity.ok(foodInformation);
    }
}
