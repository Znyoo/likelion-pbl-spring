package com.example.likelion_pbl_spring.assignment.repository;

import com.example.likelion_pbl_spring.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByMemberId(Long memberId);
}