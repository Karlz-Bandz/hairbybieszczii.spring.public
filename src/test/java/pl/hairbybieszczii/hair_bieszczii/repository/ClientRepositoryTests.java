package pl.hairbybieszczii.hair_bieszczii.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ClientRepository;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientRepositoryTests {
    @Autowired
    private ClientRepository clientRepository;

  public EntityClient createClient(){
      EntityClient entityClient = new EntityClient();
      entityClient.setClientName("Marek");
      entityClient.setClientSurname("Mazurek");
      entityClient.setPhoneNumber("555-66-666");
      return clientRepository.save(entityClient);
  }


    @Test
    public void ClientRepository_SaveAll_ReturnSavedClient(){


        //Arrange
        EntityClient entityClient = createClient();



        //Act
         EntityClient savedClient = clientRepository.save(entityClient);


        //Assert
        Assertions.assertThat(savedClient).isNotNull();
        Assertions.assertThat(savedClient.getId()).isGreaterThan(0);
    }

    @Test
    public void ClientRepository_existsByClientName_ReturnIfClientExists(){

        boolean answer = true;
        boolean answerFalse = false;

        EntityClient entityClient = createClient();

        boolean solution = clientRepository.existsByClientName("Marek");
        boolean solutionFalse = clientRepository.existsByClientName("Karol");


        Assertions.assertThat(solution).isEqualTo(answer);
        Assertions.assertThat(solutionFalse).isEqualTo(answerFalse);
    }

    @Test
    public void ClientRepository_FindById_ReturnClient(){

        EntityClient entityClient = createClient();

        EntityClient foundClient = clientRepository.findById(entityClient.getId()).get();

        Assertions.assertThat(foundClient.getClientName()).isEqualTo("Marek");

    }
    @Test
    public void ClientRepository_ExistsBySurname_ReturnIfExists(){

      EntityClient entityClient = createClient();

      boolean expectedVal = true;
      boolean solution = clientRepository.existsByClientSurname("Mazurek");

      Assertions.assertThat(solution).isEqualTo(expectedVal);

    }
    @Test
    public void ClientRepository_GetClients_ReturnListClients(){
      EntityClient entityClient = createClient();
      EntityClient entityClient2 = createClient();

        List<EntityClient> clients = clientRepository.getClients();

        Assertions.assertThat(clients.size()).isEqualTo(2);
    }

    @Test
    public void ClientRepository_GetSelectedClients_ReturnListSelectedClients(){


        EntityClient entityClient = createClient();
        EntityClient entityClient1 = createClient();

        List<SelectBoxClientModel> clients = clientRepository.getSelectClients();

        Assertions.assertThat(clients.size()).isEqualTo(2);

    }
}
