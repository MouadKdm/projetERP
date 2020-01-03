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
public class Agence implements Serializable
{
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;
	    String nomAgence;
	    @Embedded
	    Adress adress ;
	@JsonIgnore

	@OneToMany(mappedBy = "agence")
        List<Agent> agents;

}
