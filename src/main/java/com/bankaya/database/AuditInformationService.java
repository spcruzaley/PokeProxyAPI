package com.bankaya.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AuditInformationService {

    private final AuditInformationRepository auditInformationRepository;
    private final HttpServletRequest servletRequest;

    @Autowired
    public AuditInformationService(AuditInformationRepository auditInformationRepository,
                                   HttpServletRequest servletRequest) {
        this.auditInformationRepository = auditInformationRepository;
        this.servletRequest = servletRequest;
    }

    private AuditInformation save(AuditInformation auditInformation) {
        return auditInformationRepository.save(auditInformation);
    }

    public void save(String method) {
        AuditInformation auditInformation = AuditInformation.builder()
                .ip(servletRequest.getRemoteAddr())
                .dateTime(LocalDateTime.now())
                .method(method)
                .build();
        save(auditInformation);

        log.info("Information saved {}", auditInformation);
    }
}
