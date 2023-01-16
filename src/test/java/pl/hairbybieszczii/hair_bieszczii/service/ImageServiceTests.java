package pl.hairbybieszczii.hair_bieszczii.service;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import pl.hairbybieszczii.hair_bieszczii.model.ImageModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ImageServiceTests {


   @Mock
   private ImageRepository imageRepository;

   @InjectMocks
   private ImageService imageService;

   private ImageModel imageModel;
   private ImageModel imageModel2;


   @BeforeEach
    public void init(){
       imageModel = new ImageModel(1L, "user/img.jpg");
       imageModel2 = new ImageModel(2L, "user/img2.jpg");



   }

   @Test
   public void ImageService_getImage_ReturnImage(){

      when(imageRepository.findById(imageModel.getId()))
              .thenReturn(Optional.ofNullable(imageModel));
      when(imageRepository.findById(imageModel2.getId()))
              .thenReturn(Optional.ofNullable(imageModel2));

      ImageModel returnedTest = imageService.getImage(imageModel.getId());
      ImageModel returnedTest2 = imageService.getImage(imageModel2.getId());

      Assertions.assertThat(returnedTest).isNotNull();
      Assertions.assertThat(returnedTest.getUrl()).isEqualTo("user/img.jpg");
      Assertions.assertThat(returnedTest.getId()).isEqualTo(1L);

      Assertions.assertThat(returnedTest2).isNotNull();
      Assertions.assertThat(returnedTest2.getUrl()).isEqualTo("user/img2.jpg");
      Assertions.assertThat(returnedTest2.getId()).isEqualTo(2L);

   }
   @Test
   public void ImageService_getAllImages_ReturnListImages(){
      List<ImageModel> repositoryTest = new ArrayList<>();
      repositoryTest.add(imageModel);
      repositoryTest.add(imageModel2);

      when(imageRepository.findAll()).thenReturn(repositoryTest);

      List<ImageModel> returnedRepositoryTest = imageService.getImages();

      Assertions.assertThat(returnedRepositoryTest).isNotNull();
      Assertions.assertThat(returnedRepositoryTest.size()).isEqualTo(1);

   }



}
