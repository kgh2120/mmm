package com.spring.mmm.domain.mukgroups.infra;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MukgroupJpaRepository extends JpaRepository<MukgroupEntity, Long> {
    @Query("select mg from MukgroupEntity mg where mg.mukgroupId=:mukgroupId")
    Optional<MukgroupEntity> findByMukgroupId(@Param("mukgroupId") Long mukgroupId);

    @Query("select count(mb) from MukboEntity mb where mb.mukgroupEntity.mukgroupId=:mukgroupId")
    Integer countAllMukboByMukgroupId(@Param("mukgroupId") Long mukgroupId);
}
