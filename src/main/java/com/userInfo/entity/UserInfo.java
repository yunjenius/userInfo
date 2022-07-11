package com.userInfo.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserInfo {

    @Id
    private String userEmail;

    private String userNickname;
    private String userPwd;
    private String userName;
    private String userPhone;

}
