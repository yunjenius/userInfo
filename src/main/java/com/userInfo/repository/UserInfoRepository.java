package com.userInfo.repository;

import com.userInfo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserPhone(String userPhone);

    UserInfo findByUserEmail(String userEmail);

}
