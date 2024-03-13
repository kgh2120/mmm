package com.spring.mmm.domain.recommends.infra;

import com.spring.mmm.domain.mbtis.infra.MukBTIEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "food_mbti")
@Entity
public class FoodMBTIEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_mbti_id")
    private Integer foodMbtiId;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private FoodEntity foodEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbti_id")
    private MukBTIEntity mukBTIEntity;
}
