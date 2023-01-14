package pl.hairbybieszczii.hair_bieszczii.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionDto {
    private int id;
    private String description;
}
