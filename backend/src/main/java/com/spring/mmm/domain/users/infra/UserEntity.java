package com.spring.mmm.domain.users.infra;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.users.event.UserDeletedEvent;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<MukboEntity> mukboEntities;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIResultEntity> mukBTIResultEntities;

    public static UserEntity create(UserJoinRequest userJoinRequest, String encodedPW) {
        return UserEntity.builder()
                .email(userJoinRequest.getEmail())
                .nickname(userJoinRequest.getNickname())
                .password(encodedPW)
                .build();
    }

    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public void deleteUser() {
        Events.raise(UserDeletedEvent.create(this.id));
    }

}
