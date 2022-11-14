package crm.crmbackend.entity;

import crm.crmbackend.common.Tracker;
import crm.crmbackend.enumeration.PublicationPeriod;
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
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publication")
public class Publication extends Tracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate firstIssueDate;

    @Enumerated(EnumType.STRING)
    private PublicationPeriod issuePeriod;

    private String comesOut;

    private BigDecimal price;

    private Boolean active;

    @OneToMany(mappedBy = "publication")
    private List<Subscription> subscriptions;
}
