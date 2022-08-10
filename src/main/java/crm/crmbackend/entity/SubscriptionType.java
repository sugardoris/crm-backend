package crm.crmbackend.entity;

import crm.crmbackend.common.Tracker;
import crm.crmbackend.enumeration.SubscriptionPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription_type")
@Entity
public class SubscriptionType extends Tracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    private SubscriptionPeriod subscriptionPeriod;

    private boolean active;

    @OneToMany(mappedBy = "subscriptionType")
    private List<Subscription> subscriptions;
}
