package api.erp.banqueservice.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Adress implements Serializable {
    private String ville ;
    private String quartie ;
    private String immeuble ;
    private String telephone ;
    String city;
    String state;
    String zipCode;
    String country;

    public Adress() {
    }

    public Adress(String ville, String quartie, String immeuble, String telephone) {
        this.ville = ville;
        this.quartie = quartie;
        this.immeuble = immeuble;
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartie() {
        return quartie;
    }

    public void setQuartie(String quartie) {
        this.quartie = quartie;
    }

    public String getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(String immeuble) {
        this.immeuble = immeuble;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
