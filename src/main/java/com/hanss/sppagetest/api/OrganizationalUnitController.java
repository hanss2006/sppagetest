package com.hanss.sppagetest.api;

import com.hanss.sppagetest.model.OrganizationalUnit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class OrganizationalUnitController {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @GetMapping("/organizational_units")
    public ResponseEntity<List<OrganizationalUnit>> getOrganizationalUnits(
            @RequestParam(name = "page_number") Integer pageNumber,
            @RequestParam(name = "page_size")  Integer pageSize
    ) {
        List<OrganizationalUnit> employees = entityManager
                .createNamedStoredProcedureQuery("sp_get_organizational_unit")
                .setParameter("page_number", pageNumber)
                .setParameter("page_size", pageSize)
                .getResultList();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
