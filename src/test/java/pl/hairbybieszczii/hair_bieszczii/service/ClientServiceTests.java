package pl.hairbybieszczii.hair_bieszczii.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClientDescription;
import pl.hairbybieszczii.hair_bieszczii.repo.ClientRepository;
import pl.hairbybieszczii.hair_bieszczii.service.client.ClientService;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientServiceTests {


     @Mock
     private ClientRepository clientRepository;

     @InjectMocks
     private ClientService clientService;

     @Test
     public void ClientService_AddNewClient_ReturnClient(){

         EntityClient entityClient = EntityClient.builder()
                 .clientName("John")
                 .clientSurname("Lennon")
                 .phoneNumber("666-444-333")
                 .build();

          ClientDto clientDto = ClientDto.builder()
                  .clientName("Paul")
                  .clientSurname("McCartney")
                  .phoneNumber("22-333-44-55")
                  .build();

          //1 Example of mocking

//          when(clientRepository.save(Mockito.any(EntityClient.class)))
//                  .thenReturn(entityClient);

         //2 Example of mocking

         doReturn(entityClient).when(clientRepository).save(Mockito.any(EntityClient.class));



         ClientDto client = clientService.addNewClient(clientDto);

         Assertions.assertThat(client).isNotNull();
         Assertions.assertThat(client.getClientName()).isEqualTo("Paul");


     }
     @Test
     public void ClientService_ExistsById_ReturnIfExists(){
         EntityClient client = EntityClient.builder()

                 .clientName("Karol")
                 .clientSurname("Melek")
                 .phoneNumber("333")
                 .build();
         when(clientRepository.existsById(client.getId())).thenReturn(true);

         boolean returnedTest = clientService.existsById(client.getId());

         verify(clientRepository, times(1)).existsById(client.getId());
         verifyNoMoreInteractions(clientRepository);

         Assertions.assertThat(returnedTest).isEqualTo(true);
     }

     @Test
     public void ClientService_AddNewDescription_Return_Test(){
        EntityClient client = EntityClient.builder()
                .id(1)
                .clientName("Mariusz")
                .clientSurname("Test")
                .phoneNumber("33-44")
                .build();
         ClientDto clientDto = ClientDto.builder()

                 .clientName("Mariusz")
                 .clientSurname("Test")
                 .phoneNumber("33-44")
                 .build();
         DescriptionDto description = DescriptionDto.builder()
                         .id(1)
                                 .description("Test")
                                         .build();
         EntityClientDescription entityDescription = EntityClientDescription.builder()
                         .description("Test")
                                 .build();
//         client.addToList(entityDescription);

         when(clientRepository.save(Mockito.any(EntityClient.class)))
                 .thenReturn(client);



         ClientDto savedClient = clientService.addNewClient(clientDto);

//                  when(clientRepository.findById(1))
//                 .thenReturn(Optional.ofNullable(client));


//         boolean solution = clientService.addNewDescription(description);



         Assertions.assertThat(savedClient).isNotNull();
//         Assertions.assertThat(solution).isEqualTo(true);


//         ClientDto clientTest = clientService.addNewClient(clientDto);
//
//
//
//         doReturn(client).when(clientRepository).save(Mockito.any(EntityClient.class));
//         when(clientRepository.save(Mockito.any(EntityClient.class)))
//                 .thenReturn(client);
//         when(clientRepository.findById(description.getId()))
//                 .thenReturn(Optional.ofNullable(client));


//        ClientDto clientTest = clientService.addNewClient(clientDto);



//         boolean solution = clientService.addNewDescription(description);



//         Assertions.assertThat(clientTest).isNotNull();





     }
}
