package crm.crmbackend.entity;

import crm.crmbackend.enumeration.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "id")
    private Subscriber subscriber;

    private BigDecimal amount;

    private LocalDate dueDate;

    private Period period;

    private Boolean paid;
}
