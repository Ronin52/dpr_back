package ru.ronin52.dpr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ronin52.dpr.model.KnowledgeBaseItemType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeBaseDto {
    private String pointId;
    private String pointText;
    private KnowledgeBaseItemType pointType;
}
