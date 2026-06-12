package com.cacheable;

import jakarta.validation.constraints.*;
import org.antlr.v4.runtime.misc.NotNull;

public class StudentDtos {
  public record CreateStudentRequest(
      @NotBlank String name,
      @NotNull @Min(1) @Max(12) Integer grade,
      @NotNull Long parentUserId
  ) {}

  public record StudentResponse(
      Long id, String name, Integer grade, Long parentUserId, String parentEmail
  ) {}
}