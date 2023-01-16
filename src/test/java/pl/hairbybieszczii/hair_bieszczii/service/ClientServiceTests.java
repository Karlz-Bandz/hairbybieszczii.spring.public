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
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ClientRepository;
import pl.hairbybieszczii.hair_bieszczii.repo.DescriptionRepository;
import pl.hairbybieszczii.hair_bieszczii.service.client.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientServiceTests {


    @Mock
    private ClientRepository clientRepository;

    @Mock
    private DescriptionRepository descriptionRepository;

    @InjectMocks
    private ClientService clientService;

    private EntityClient entityClient = EntityClient.builder()

            .clientName("John")
            .clientSurname("Lennon")
            .phoneNumber("666-444-333")
            .build();


    private ClientDto clientDto = ClientDto.builder()

            .clientName("John")
            .clientSurname("Lennon")
            .phoneNumber("666-444-333")
            .build();

    @Test
    public void ClientService_AddNewClient_ReturnClient() {


        //1 Example of mocking

//          when(clientRepository.save(Mockito.any(EntityClient.class)))
//                  .thenReturn(entityClient);

        //2 Example of mocking

        doReturn(entityClient).when(clientRepository).save(Mockito.any(EntityClient.class));


        ClientDto client = clientService.addNewClient(clientDto);

        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getClientName()).isEqualTo("John");


    }

    @Test
    public void ClientService_ExistsById_ReturnIfExists() {

        when(clientRepository.existsById(entityClient.getId())).thenReturn(true);

        boolean returnedTest = clientService.existsById(entityClient.getId());

        Assertions.assertThat(returnedTest).isEqualTo(true);
    }

    @Test
    public void ClientService_AddNewDescription_Return_Test() {

        DescriptionDto description = DescriptionDto.builder()
                .id(entityClient.getId())
                .description("Test")
                .build();

        when(clientRepository.findById(entityClient.getId())).thenReturn(Optional.of(entityClient));
        when(clientRepository.save(Mockito.any(EntityClient.class))).thenReturn(entityClient);

        boolean returnedTest = clientService.addNewDescription(description);

        Assertions.assertThat(returnedTest).isEqualTo(true);
    }

    @Test
    public void ClientService_DeleteClientById_ClientDeleted() {

        clientService.deleteClientById(entityClient.getId());
        verify(clientRepository).deleteById(entityClient.getId());

    }
    @Test
    public void ClientService_DeleteDescriptionById_DescriptionDeleted(){

        EntityClientDescription descriptionTest = EntityClientDescription.builder()
                        .description("Test")
                                .build();

        clientService.deleteDescriptionById(descriptionTest.getId());

        verify(descriptionRepository).deleteById(descriptionTest.getId());


    }

    @Test
    public void ClientService_getSelectClient_ReturnSelectClients(){

        SelectBoxClientModel selectClient = SelectBoxClientModel.builder()
                .id(0)
                .clientName("Karol")
                .clientSurname("Melek")
                .build();

        SelectBoxClientModel selectClient2 = SelectBoxClientModel.builder()
                .id(1)
                .clientName("Piotrek")
                .clientSurname("Melek")
                .build();

        List<SelectBoxClientModel> clientsTest = new ArrayList<>();
        clientsTest.add(selectClient);
        clientsTest.add(selectClient2);

        when(clientRepository.getSelectClients()).thenReturn(clientsTest);

        List<SelectBoxClientModel> solutionList = clientService.getSelectClient();

        Assertions.assertThat(solutionList).isNotNull();
        Assertions.assertThat(solutionList.size()).isEqualTo(2);

        System.out.println(solutionList.stream()
                .collect(Collectors.toList()));
    }
    @Test
    public void ClientService_showChosenClientById(){

        when(clientRepository.findById(entityClient.getId()))
                .thenReturn(Optional.of(entityClient));

        EntityClient returnedTest = clientService.showChosenClientById(entityClient.getId());

        Assertions.assertThat(returnedTest).isNotNull();


    }
}
