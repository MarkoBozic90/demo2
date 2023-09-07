package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentDto save(StudentDto studentDto) {
        Student entity = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public StudentDto findById(Long id) {
        return studentMapper.toDto(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    public Page<StudentDto> findByCondition(StudentDto studentDto, Pageable pageable) {
        Page<Student> entityPage = repository.findAll(pageable);
        List<Student> entities = entityPage.getContent();
        return new PageImpl<>(studentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public StudentDto update(StudentDto studentDto, Long id) {
        StudentDto data = findById(id);
        Student entity = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(repository.saveAndFlush(entity));
    }
}