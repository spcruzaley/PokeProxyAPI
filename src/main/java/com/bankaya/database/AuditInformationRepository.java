package com.bankaya.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditInformationRepository extends CrudRepository<AuditInformation, Long> {
}
