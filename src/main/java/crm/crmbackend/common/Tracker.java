package crm.crmbackend.common;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Tracker {

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @PrePersist
    protected void prePersist() {
        if (createDate == null) {
            createDate = LocalDateTime.now();
            lastUpdate = createDate;
        }
        if (createdBy == null) {
            //TODO: createdBy = currently logged in user
            //TODO: updatedBy = createdBy
        }
    }

    @PreUpdate
    protected void preUpdate() {
        lastUpdate = LocalDateTime.now();
        //TODO: updatedBy = currently logged in user
    }
}
