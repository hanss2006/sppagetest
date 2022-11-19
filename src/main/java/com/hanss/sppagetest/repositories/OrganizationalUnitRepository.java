package com.hanss.sppagetest.repositories;

import com.hanss.sppagetest.model.OrganizationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, Integer> {
    @Override
    long count();
}