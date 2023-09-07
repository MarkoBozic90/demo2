package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/student")
@RestController
@Slf4j
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated StudentDto studentDto) {
        studentService.save(studentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable("id") Long id) {
        StudentDto student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
      try {
            studentService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
      }
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<StudentDto>> pageQuery(StudentDto studentDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<StudentDto> studentPage = studentService.findByCondition(studentDto, pageable);
        return ResponseEntity.ok(studentPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated StudentDto studentDto, @PathVariable("id") Long id) {
        studentService.update(studentDto, id);
        return ResponseEntity.ok().build();
    }
}