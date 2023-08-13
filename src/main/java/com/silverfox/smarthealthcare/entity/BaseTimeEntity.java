package com.silverfox.smarthealthcare.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate // 엔티티 생성 시, 당시 시각을 자동 기입!
    private LocalDateTime createdDate;

    @LastModifiedDate // 엔티티 수정 시, 당시 시각을 자동 기입!
    private LocalDateTime lastModifiedDate;

}
