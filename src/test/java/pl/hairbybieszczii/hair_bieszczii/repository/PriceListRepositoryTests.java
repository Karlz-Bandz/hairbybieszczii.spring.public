package pl.hairbybieszczii.hair_bieszczii.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.hairbybieszczii.hair_bieszczii.model.PriceListModel;
import pl.hairbybieszczii.hair_bieszczii.repo.PriceListRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PriceListRepositoryTests {

    @Autowired
    private PriceListRepository priceListRepository;

    @Test
    public void PriceListRepository_saveAll_Test(){
        PriceListModel priceListModel = PriceListModel.builder()
                .name("test")
                .description("test")
                .price("35zl")
                .build();

        PriceListModel solution = priceListRepository.save(priceListModel);


        Assertions.assertThat(solution).isNotNull();
        Assertions.assertThat(solution.getId()).isGreaterThan(0);
    }




    @Test
    public void PriceListRepository_findAll_ReturnAllPrices(){


        PriceListModel priceListModel = new PriceListModel(1L,"Test", "test", "50zl");
        PriceListModel priceListModel2 = new PriceListModel(2L,"Test2", "test2", "10zl");

        priceListRepository.save(priceListModel);
        priceListRepository.save(priceListModel2);

        List<PriceListModel> prices = priceListRepository.findAll();

        Assertions.assertThat(prices.size()).isEqualTo(2);

    }


}

