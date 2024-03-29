package crm.crmbackend.repository;

import crm.crmbackend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllBySubscriptionType_IdAndDateEndedAfter(Long subscriptionTypeId, LocalDate dateEnded);

    List<Subscription> findAllBySubscriber_Id(Long subscriberId);
}
