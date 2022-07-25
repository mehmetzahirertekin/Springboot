package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private Optional<Object> studentOptional;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws IllegalAccessException {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalAccessException("email taken");
        }
        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) throws IllegalAccessException {
        studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalAccessException("student with id "+ studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) throws IllegalAccessException {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("student with id "+studentId+" do es not exist"));
        if (name != null &&
        name.length()>0 &&
        !Objects.equals(student.getName(),name)) {
            student.setName(name);
        }
        if (email != null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(),email)) {
            student.setEmail(email);

            if (studentOptional.isPresent()){
                throw  new IllegalAccessException("email taken");
            }
            student.setEmail(email);
        }

    }
}
