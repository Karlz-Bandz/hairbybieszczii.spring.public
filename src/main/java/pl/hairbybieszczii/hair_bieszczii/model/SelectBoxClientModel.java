package pl.hairbybieszczii.hair_bieszczii.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SelectBoxClientModel {
    private int id;
    private String clientName;
    private String clientSurname;
}
