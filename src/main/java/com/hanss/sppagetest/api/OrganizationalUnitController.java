package com.hanss.sppagetest.api;

import com.hanss.sppagetest.model.OrganizationalUnit;
import com.hanss.sppagetest.repositories.OrganizationalUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class OrganizationalUnitController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrganizationalUnitRepository organizationalUnitRepository;

    @SuppressWarnings("unchecked")
    @GetMapping("/organizational_units")
    public ResponseEntity<Page<OrganizationalUnit>> getOrganizationalUnits(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
            Pageable pageable
    ) {
        List<OrganizationalUnit> organizationalUnits = entityManager
                .createNamedStoredProcedureQuery("sp_get_organizational_unit")
                .setParameter("Page_number", pageable.getPageNumber())
                .setParameter("Page_size", pageable.getPageSize())
                .getResultList();
        Page<OrganizationalUnit> pageOrgs = new PageImpl<>(
                organizationalUnits,
                pageable,
                organizationalUnitRepository.count()
        );
        return new ResponseEntity<>(pageOrgs, HttpStatus.OK);
    }
}
