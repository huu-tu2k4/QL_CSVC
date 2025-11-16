package com.project_1.ql_trang_thi_bi.repositorys;

import com.project_1.ql_trang_thi_bi.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByCode(String code);
}
