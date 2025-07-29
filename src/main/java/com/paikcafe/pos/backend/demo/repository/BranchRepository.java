package com.paikcafe.pos.backend.demo.repository;

import com.paikcafe.pos.backend.demo.entity.Branch;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
}
