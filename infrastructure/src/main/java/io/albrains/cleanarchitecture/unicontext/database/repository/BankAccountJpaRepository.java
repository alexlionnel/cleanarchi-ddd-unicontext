package io.albrains.cleanarchitecture.unicontext.database.repository;

import io.albrains.cleanarchitecture.unicontext.database.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountJpaRepository extends JpaRepository<BankAccountEntity, Long> {
}
