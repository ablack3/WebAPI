/**
 * This file was generated by the JPA Modeler
 */ 

package org.ohdsi.webapi.study;

import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Chris Knoll <cknoll@ohdsi.org>
 */

@Entity
@Table(name="STUDY_COHORT")
public class StudyCohort { 

    @Id
    @GeneratedValue(generator="STUDY_COHORT_SEQ",strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="STUDY_COHORT_SEQ",sequenceName="STUDY_COHORT_SEQ",allocationSize=1)
    private Integer id;

    @Column(columnDefinition="VARCHAR(256)")
    @Basic
    private String name;

    @Column(columnDefinition="VARCHAR(2000)")
    @Basic
    private String description;

    @Column(columnDefinition="VARCHAR(10)")
    @Basic
    private String abbr;

    @Column(columnDefinition="VARCHAR(MAX)")
    @Lob
    @Basic
    private String expression;

    @ElementCollection(fetch=FetchType.LAZY)
    @CollectionTable(name="STUDY_COHORT_CONCEPT",joinColumns={@JoinColumn(name="STUDY_COHORT_ID",referencedColumnName="ID")})
    private List<Concept> concepts;

    @ElementCollection
    @CollectionTable(name="STUDY_COHORT_REL",joinColumns={@JoinColumn(name="COHORT_1_ID",referencedColumnName="ID")})
    private Set<CohortRelationship> cohortRelationships = new java.util.HashSet<>();


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Concept> getConcepts() {
        return this.concepts;
    }

    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public Set<CohortRelationship> getCohortRelationships() {
        return this.cohortRelationships;
    }

    public void setCohortRelationships(Set<CohortRelationship> cohortRelationships) {
        this.cohortRelationships = cohortRelationships;
    }


}
