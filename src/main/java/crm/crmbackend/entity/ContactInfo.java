package crm.crmbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_info")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String companyName;

    private String oib;

    private String email;

    private String phone1;

    private String phone2;

    private String billingAddress;
    
    private Boolean legalEntity;

    @ManyToOne
    @JoinColumn(name = "postcode", referencedColumnName = "postcode")
    private City city;

    @OneToOne(mappedBy = "contactInfo")
    private Subscriber subscriber;
}
