package pl.hairbybieszczii.hair_bieszczii.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



import java.io.Serializable;

@Entity
@Table(name = "ImageUrl")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String Url;
}
