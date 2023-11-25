package com.example.samuraitravel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshtokenRequest {
    private String mailaddress;
    private String password;
}
