package com.hackerton.demo.domain.Result;

import com.hackerton.demo.domain.mapping.Keyword;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Result {

    @Id @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL)     // keyword 양방향 매핑
    private List<Keyword> keywords = new ArrayList<>();
}
