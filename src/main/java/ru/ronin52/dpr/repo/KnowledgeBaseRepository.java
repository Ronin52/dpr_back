package ru.ronin52.dpr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ronin52.dpr.model.KnowledgeBaseEntity;


public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBaseEntity, String> {


}
