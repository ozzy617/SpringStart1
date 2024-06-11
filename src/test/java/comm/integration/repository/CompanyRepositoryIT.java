package comm.integration.repository;

import comm.annotation.IT;
import comm.database.entity.Company;
import comm.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@IT
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
//@Commit
public class CompanyRepositoryIT {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void findByIdTest() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(1);
    }

    @Test
    void saveTest() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple descr"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }

    @Test
    void delete() {
        var maybeCompany = companyRepository.findById(6);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(6).isEmpty());
    }

    @Test
    void chekFindByQueries() {
        companyRepository.findByName("Google");
//        var companies = companyRepository.findAllByNameContainingIgnoreCase("a");
       // assertThat(companies).hasSize(2);
    }
}

