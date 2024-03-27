package com.spring.mmm.domain.recommends.service;

import com.spring.mmm.domain.mbtis.domain.MukBTIEntity;
import com.spring.mmm.domain.mbtis.domain.MukBTIType;
import com.spring.mmm.domain.recommends.controller.request.LunchRecommendRequest;
import com.spring.mmm.domain.recommends.controller.response.LunchRecommendFoodInformation;
import com.spring.mmm.domain.recommends.domain.FoodCategoryEntity;
import com.spring.mmm.domain.recommends.domain.FoodEntity;
import com.spring.mmm.domain.recommends.domain.FoodMBTIEntity;
import com.spring.mmm.domain.recommends.service.port.FoodRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecommendServiceImplTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private RecommendServiceImpl recommendService;

    static private List<FoodEntity> foods;
    static private List<FoodMBTIEntity> foodMBTIEntities;
    static private LunchRecommendRequest lunchRecommendRequest;

    @BeforeAll
    static void 음식장전(){
        foodMBTIEntities = new ArrayList<>();

        int t = 1;
        for(MukBTIType type : MukBTIType.values()) {
            foodMBTIEntities.add(FoodMBTIEntity.builder()
                    .foodMbtiId(t)
                    .score(t * 10)
                    .mukBTIEntity(MukBTIEntity.builder()
                            .mukbtiId(t++)
                            .type(type)
                            .build())
                    .build());
        }
        // 음식 먹비티아이장전

        foods = new ArrayList<>();
        for(int i = 1; i <= 97; i++){
            foods.add(FoodEntity.builder()
                    .foodId(i)
                    .name(Integer.toString(i))
                    .image(Integer.toString(i))
                    .foodMBTIEntities(foodMBTIEntities)
                            .foodCategoryEntity(FoodCategoryEntity.builder()
                                    .foodCategoryId(i)
                                    .build()
                            )
                    .build());
        }
        // 음식장전

        lunchRecommendRequest = LunchRecommendRequest.builder()
                .EI(50)
                .NS(50)
                .TF(50)
                .JP(50)
                .build();
        // 점심추천요청장전
    }

    @Test
    void 랜덤음식_일곱개_추천_성공(){
        BDDMockito.given(foodRepository.findAll())
                .willReturn(foods);

        assertEquals(7, recommendService.recommendRandomFood().size());
    }

    @Test
    void 점심추천_성공(){
        BDDMockito.given(foodRepository.findAll())
                .willReturn(foods);

        assertEquals(7, recommendService.lunchRecommendFood(lunchRecommendRequest).size());
    }

}