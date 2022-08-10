package crm.crmbackend.entity;

import crm.crmbackend.enumeration.PublicationPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publishing_info")
public class PublishingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate firstIssueDate;

    @Enumerated(EnumType.STRING)
    private PublicationPeriod issuePeriod;

    private String comesOut;

    private BigDecimal price;

    @OneToOne(mappedBy = "publishingInfo")
    private Publication publication;
}
