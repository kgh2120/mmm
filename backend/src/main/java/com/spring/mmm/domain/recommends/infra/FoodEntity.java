package com.spring.mmm.domain.recommends.infra;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food")
@Entity
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategoryEntity foodCategoryEntity;

    @OneToMany(mappedBy = "foodEntity")
    private List<RecommendedFoodEntity> recommendedFoodEntities;

    @OneToMany(mappedBy = "foodEntity")
    private List<FoodMBTIEntity> foodMBTIEntities;
}
