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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription")
@Entity
public class Subscription extends Tracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private SubscriptionType subscriptionType;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "id")
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;

    private LocalDate dateStarted;

    private LocalDate dateEnded;

    private BigDecimal price;
}
