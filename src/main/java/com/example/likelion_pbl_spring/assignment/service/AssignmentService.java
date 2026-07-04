package com.example.likelion_pbl_spring.assignment.service;

import com.example.likelion_pbl_spring.member.domain.Member;
import com.example.likelion_pbl_spring.member.repository.MemberRepository;
import com.example.likelion_pbl_spring.assignment.domain.Assignment;
import com.example.likelion_pbl_spring.assignment.dto.AssignmentCreateRequest;
import com.example.likelion_pbl_spring.assignment.dto.AssignmentUpdateRequest;
import com.example.likelion_pbl_spring.assignment.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Assignment createAssignment(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과제입니다."));
    }

    @Transactional
    public Assignment updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과제입니다."));

        assignment.update(request.getTitle(), request.getDescription());
        return assignment;
    }

    @Transactional
    public void deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 과제입니다.");
        }
        assignmentRepository.deleteById(id);
    }
}