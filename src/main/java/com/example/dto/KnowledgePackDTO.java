package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KnowledgePackDTO {

    @Pattern(regexp = "^[1-9]\\d{1,18}$", message = "Only numbers up to 19 nums allowed")
    @Max(Long.MAX_VALUE)
    @Min(1L)
    private Long id;

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[A-Za-z0-9 ]{1,250}$", message = "Title must be 1-250 characters, only letters, numbers, and spaces")
    private String title;

    @Pattern(regexp = "^[A-Za-z0-9 ]{1,2000}$", message = "Description must be 1-2000 characters, only letters, numbers, and spaces")
    private String description;

    @NotBlank(message = "Creation date is required")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$", message = "Date format DD-MM-YYYY only allowed")
    private String creationDate;
}
