package org.ohdsi.webapi.test.entity;

import static org.ohdsi.webapi.service.ConceptSetService.COPY_NAME;
import static org.ohdsi.webapi.test.TestConstants.NEW_TEST_ENTITY;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ohdsi.webapi.WebApi;
import org.ohdsi.webapi.conceptset.ConceptSetRepository;
import org.ohdsi.webapi.service.ConceptSetService;
import org.ohdsi.webapi.service.dto.ConceptSetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest(classes = WebApi.class)
@TestPropertySource(locations = "/in-memory-webapi.properties")
public class ConceptSetEntity implements TestCreate, TestCopy {
    @Autowired
    protected ConceptSetService csService;
    @Autowired
    protected ConceptSetRepository csRepository;
    private ConceptSetDTO firstSavedDTO;

    // in JUnit 4 it's impossible to mark methods inside interface with annotations, it was implemented in JUnit 5. After upgrade it's needed
    // to mark interface methods with @Test, @Before, @After and to remove them from this class
    @After
    @Override
    public void tearDownDB() {

        csRepository.deleteAll();
    }

    @Before
    @Override
    public void init() throws Exception {

        TestCreate.super.init();
    }

    @Test
    @Override
    public void shouldNotCreateEntityWithDuplicateName() {

        TestCreate.super.shouldNotCreateEntityWithDuplicateName();
    }

    @Test
    @Override
    public void shouldCopyWithUniqueName() throws Exception {

        TestCopy.super.shouldCopyWithUniqueName();
    }

    @Test
    @Override
    public void shouldCopyFromCopy() throws Exception {

        TestCopy.super.shouldCopyFromCopy();
    }

    @Test
    @Override
    public void shouldCopySeveralTimesOriginal() throws Exception {

        TestCopy.super.shouldCopySeveralTimesOriginal();
    }

    @Test
    @Parameters({
            "abcde, abc, abc", "abcde (1), abcde, abcde (2)"
    })
    @Override
    public void shouldCopyOfPartlySameName(String firstName, String secondName, String assertionName) throws Exception {

        TestCopy.super.shouldCopyOfPartlySameName(firstName, secondName, assertionName);
    }

    @Override
    public Object createCopy(Object dto) {

        ConceptSetDTO castedDTO = (ConceptSetDTO) dto;
        castedDTO.setName(csService.getNameForCopy(castedDTO.getId()).get(COPY_NAME));
        return csService.createConceptSet(castedDTO);
    }

    @Override
    public String getDtoName(Object dto) {

        return ((ConceptSetDTO) dto).getName();
    }

    @Override
    public void initFirstDTO() {

        firstSavedDTO = createEntity(NEW_TEST_ENTITY);
    }

    @Override
    public Object getFirstSavedDTO() {

        return firstSavedDTO;
    }

    @Override
    public ConceptSetDTO createEntity(String name) {

        ConceptSetDTO dto = new ConceptSetDTO();
        dto.setName(name);
        return csService.createConceptSet(dto);
    }

    @Override
    public String getConstraintName() {

        return "uq_cs_name";
    }
}
