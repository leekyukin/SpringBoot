package com.study.jpaentitylistener.domain.listener;

import com.study.jpaentitylistener.domain.User;
import com.study.jpaentitylistener.domain.UserHistory;
import com.study.jpaentitylistener.repository.UserHistoryRepository;
import com.study.jpaentitylistener.support.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        // ApplicationContextAware 의 메소드를 이용한 주입

        User user = (User) o;

        UserHistory userHistory = new UserHistory();

        userHistory.setUserId(user.getId());
        userHistory.setEmail(user.getEmail());
        userHistory.setName(user.getName());

        userHistoryRepository.save(userHistory);
    }
}
