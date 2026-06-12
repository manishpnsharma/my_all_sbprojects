package com.cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

  @Autowired
  StudentRepository repository;
  // find existing record
  @Cacheable(cacheNames = {"studentCache"}, key = "#studentId")
  public Student findStudent(Long studentId) {
    // simulating backend querying delay
    simulateBackendCall();
    System.out.println("Fetching student from Databse" +studentId);
    return repository.findById(studentId).get();
  }

  // find existing record
  @Cacheable(cacheNames = {"radisCache"}, key = "#studentId")
  public Student radisCache_RadisCache(Long studentId) {
    // simulating backend querying delay
    simulateBackendCall();
    System.out.println("Fetching student from Databse" +studentId);
    return repository.findById(studentId).get();
  }
  @CachePut(cacheNames = {"studentCache"}, key="#student.studentId")
  public Student updateStudent(Student student) {
    return repository.save(student);
  }
  public Student addNewStudent(Student student) {
    return repository.save(student);
  }
  @CacheEvict(cacheNames = {"studentCache"}, key = "#studentId")
  public String deleteStudent(Long studentId) {
    repository.deleteById(studentId);
    return "record deleted successfully";
  }
  // this method will pause main thread for 5 seconds
  public void simulateBackendCall() {
    try {
      System.out.println("------------- Going to sleep for 5 seconds to simulate Backend Delay -----------");
      Thread.sleep(5 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}