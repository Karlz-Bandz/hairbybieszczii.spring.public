package pl.hairbybieszczii.hair_bieszczii.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private String clientName;
    private String clientSurname;
    private String phoneNumber;
}
