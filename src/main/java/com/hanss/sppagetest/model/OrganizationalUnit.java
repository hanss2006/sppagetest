package com.hanss.sppagetest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@NamedStoredProcedureQuery(name = "sp_get_organizational_unit", procedureName = "sp_get_organizational_unit", resultClasses = {
        OrganizationalUnit.class }, parameters = {
        @StoredProcedureParameter(name = "page_number", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "page_size", mode = ParameterMode.IN, type = Integer.class)
})
public class OrganizationalUnit {

    /**
     * ID экземпляра объекта.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * Краткое наименование.
     */
    private String shortName;

    /**
     * Описание.
     */
    private String description;

    /**
     *
     */
    private String idAsutr;

}
