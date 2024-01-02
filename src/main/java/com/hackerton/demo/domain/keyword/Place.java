package com.hackerton.demo.domain.keyword;

import com.hackerton.demo.domain.User.User;
import com.hackerton.demo.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Place extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String placeName;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}
