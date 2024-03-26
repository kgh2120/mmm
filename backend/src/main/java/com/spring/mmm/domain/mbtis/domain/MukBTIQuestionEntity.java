package com.spring.mmm.domain.mbtis.domain;

import com.spring.mmm.domain.mbtis.controller.response.MukBTIQuestion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question")
@Entity
public class MukBTIQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "image_src")
    private String imageSrc;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "mukBTIQuestionEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIAnswerEntity> mukBTIAnswerEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbti_id")
    private MukBTIEntity mukBTIEntity;

    public MukBTIQuestion toQuestion() {
        return MukBTIQuestion.builder()
                .quizId(this.questionId)
                .context(this.content)
                .img(this.imageSrc != null ? this.imageSrc : null)
                .answers(mukBTIAnswerEntities.stream().map(MukBTIAnswerEntity::toAnswer)
                        .collect(Collectors.toList()))
                .build();
    }
}
