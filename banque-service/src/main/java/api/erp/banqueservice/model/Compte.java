package api.erp.banqueservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long compteId;
    String compteNumber;
    String password;
    Boolean active;
    String typeCompte;
    double soldeCompte ;
//    int minBalanceRestriction;
//    int accountBalance;
    String dateCreation;
    Boolean valid;
    String verificationCode;

    @ManyToOne
    Abonne abonne;




}
