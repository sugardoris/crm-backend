package crm.crmbackend.common;

import crm.crmbackend.entity.User;
import crm.crmbackend.security.SecurityUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.security.auth.login.CredentialNotFoundException;
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
    protected void prePersist() throws CredentialNotFoundException {
        if (createDate == null) {
            createDate = LocalDateTime.now();
            lastUpdate = createDate;
        }
        if (createdBy == null) {
            User user = SecurityUtils.getCurrentUser();
            if(user != null) {
                createdBy = user.getId();
                updatedBy = createdBy;
            } else {
                throw new CredentialNotFoundException("Currently logged in user not found.");
            }
        }
    }

    @PreUpdate
    protected void preUpdate() throws CredentialNotFoundException {
        lastUpdate = LocalDateTime.now();
        User user = SecurityUtils.getCurrentUser();
        if(user != null) {
            updatedBy = user.getId();
        } else {
            throw new CredentialNotFoundException("Currently logged in user not found.");
        }
    }
}
