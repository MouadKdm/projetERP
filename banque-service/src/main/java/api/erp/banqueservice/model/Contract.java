package api.erp.banqueservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Contract implements Serializable
{
	
	 private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 int id;
	 Boolean active;
	 @OneToOne
	 Abonne abonne;
     @ManyToOne
	 Agent agent;

}
