package com.hackerton.demo.domain.keyword;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRespository extends JpaRepository<Keyword, Long> {

    List<Keyword> findAllByUserId(Long userId);
}
