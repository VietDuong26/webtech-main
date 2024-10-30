package com.example.webtech.dto;

import com.example.webtech.entity.Category;
import com.example.webtech.entity.Color;
import com.example.webtech.entity.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private long productId;
    private String productName;
    private String price;
    private String description;
    private MultipartFile file;
    private String image;
    private Category category;
    private String[] sizes;
    private String[] colors;
}
