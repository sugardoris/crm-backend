package crm.crmbackend.common;

import crm.crmbackend.security.SecurityUtils;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Tracker {

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @PrePersist
    protected void prePersist() throws AuthenticationCredentialsNotFoundException {
        if (createDate == null) {
            createDate = LocalDateTime.now();
            lastUpdate = createDate;
        }
        if (createdBy == null) {
            String currentUser = SecurityUtils.getCurrentUserUsername();
            if(currentUser != "anonymous") {
                createdBy = currentUser;
                updatedBy = createdBy;
            } else {
                throw new AuthenticationCredentialsNotFoundException("Currently logged in user not found.");
            }
        }
    }

    @PreUpdate
    protected void preUpdate() throws AuthenticationCredentialsNotFoundException {
        lastUpdate = LocalDateTime.now();
        String currentUser = SecurityUtils.getCurrentUserUsername();
        if(currentUser != "anonymous") {
            updatedBy = currentUser;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Currently logged in user not found.");
        }
    }
}
