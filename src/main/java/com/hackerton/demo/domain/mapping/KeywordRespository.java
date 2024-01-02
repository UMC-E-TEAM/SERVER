package com.hackerton.demo.domain.mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRespository extends JpaRepository<Keyword, Long> {
}
