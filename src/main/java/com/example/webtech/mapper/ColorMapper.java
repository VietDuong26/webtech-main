package com.example.webtech.mapper;

import com.example.webtech.dto.ColorDTO;
import com.example.webtech.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {
    public ColorDTO toDto(Color color){
        return ColorDTO.builder()
                .colorId(color.getColorId())
                .colorName(color.getColorName())
                .build();
    }
}
