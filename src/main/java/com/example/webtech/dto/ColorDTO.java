package com.example.webtech.dto;

import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColorDTO {
    private long colorId;
    private String colorName;
}
