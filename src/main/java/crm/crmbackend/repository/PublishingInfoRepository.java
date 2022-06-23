package crm.crmbackend.repository;

import crm.crmbackend.entity.PublishingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingInfoRepository extends JpaRepository<PublishingInfo, Long> {
}
