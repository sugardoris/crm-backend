package crm.crmbackend.entity;

import crm.crmbackend.common.Tracker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriber")
public class Subscriber extends Tracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;

    @OneToOne(mappedBy = "subscriber")
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "subscriber")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "subscriber")
    private List<Subscription> subscriptions;
}
