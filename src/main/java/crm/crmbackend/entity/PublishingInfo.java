package crm.crmbackend.entity;


import crm.crmbackend.enumeration.Period;
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

    @OneToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private Period period;

    private String month;

    private String dateOfMonth;

    private String dayOfWeek;

    private LocalDate nextIssue;
}
