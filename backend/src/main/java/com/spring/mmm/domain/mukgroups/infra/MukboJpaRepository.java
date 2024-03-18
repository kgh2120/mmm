package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MukboJpaRepository extends JpaRepository<MukboEntity, Long> {
    @Query("select m from MukboEntity m where m.userEntity.id=:userId")
    MukboEntity findByUserId(Long userId);

    @Query("select m from MukboEntity m where m.mukGroupEntity.mukgroupId=:groupId and m.type=:mukboType")
    List<MukboEntity> findAllMukboByGroupId(Long groupId, MukboType mukboType);

    @Query("select m from MukboEntity m where m.mukboId=:mukboId")
    MukboEntity findByMukboId(Long mukboId);
}
