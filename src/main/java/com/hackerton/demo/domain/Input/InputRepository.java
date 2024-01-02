package com.hackerton.demo.domain.Input;

import com.hackerton.demo.domain.Input.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends JpaRepository<Input, Long> {

}
