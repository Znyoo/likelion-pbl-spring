package com.example.likelion_pbl_spring.assignment.controller;

import com.example.likelion_pbl_spring.assignment.domain.Assignment;
import com.example.likelion_pbl_spring.assignment.dto.AssignmentCreateRequest;
import com.example.likelion_pbl_spring.assignment.dto.AssignmentResponse;
import com.example.likelion_pbl_spring.assignment.dto.AssignmentUpdateRequest;
import com.example.likelion_pbl_spring.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request) {
        try {
            Assignment assignment = assignmentService.createAssignment(memberId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AssignmentResponse(assignment));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMember(@PathVariable Long memberId) {
        List<AssignmentResponse> responses = assignmentService.getAssignmentsByMemberId(memberId).stream()
                .map(AssignmentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id) {
        try {
            Assignment assignment = assignmentService.getAssignmentById(id);
            return ResponseEntity.ok(new AssignmentResponse(assignment));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request) {
        try {
            Assignment updated = assignmentService.updateAssignment(id, request);
            return ResponseEntity.ok(new AssignmentResponse(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        try {
            assignmentService.deleteAssignment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}