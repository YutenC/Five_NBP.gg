package com.core.act2.JpaRepository;

import com.core.act2.entity.act;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepository extends JpaRepository<act, Integer> {
}