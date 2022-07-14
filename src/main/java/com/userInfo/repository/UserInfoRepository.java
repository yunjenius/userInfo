package com.userInfo.repository;

import com.userInfo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserPhone(String userPhone);

    Optional<UserInfo> findByUserEmail(String userEmail);

}
