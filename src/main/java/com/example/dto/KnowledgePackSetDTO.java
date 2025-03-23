package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KnowledgePackSetDTO {

    @Pattern(regexp = "^[1-9]\\d{1,18}$", message = "Only numbers up to 19 nums allowed")
    @Max(Long.MAX_VALUE)
    @Min(1L)
    private Long id;

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[A-Za-z0-9 ]{1,250}$", message = "Title must be 1-250 characters, only letters, numbers, and spaces")
    private String title;

    private Set<Long> kpacs;
}
