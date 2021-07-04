package com.example.medicmongo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJson {
    String role;
    String id;
}
