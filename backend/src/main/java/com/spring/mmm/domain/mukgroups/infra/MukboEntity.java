package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mbtis.infra.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.recommends.infra.EatenMukboEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukbo")
@Entity
public class MukboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukbo_id")
    private Long mukboId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MukboType type;

    @OneToMany(mappedBy = "mukboEntity")
    private List<MukBTIResultEntity> mukBTIResultEntities;

    @OneToMany(mappedBy = "mukboEntity")
    private List<EatenMukboEntity> eatenMukboEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukGroupEntity mukGroupEntity;
}
