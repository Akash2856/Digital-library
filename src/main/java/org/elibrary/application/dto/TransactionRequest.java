package org.elibrary.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {
    @NotBlank
    String bookNo;
    @NotBlank
    String userEmail;//verify the user
}
