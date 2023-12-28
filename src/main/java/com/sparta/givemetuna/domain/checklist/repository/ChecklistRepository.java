package com.sparta.givemetuna.domain.checklist.repository;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
