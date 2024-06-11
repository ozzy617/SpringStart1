package comm.integration.service;

import comm.annotation.IT;
import comm.config.DatabaseProperties;
import comm.dto.CompanyReadDto;
import comm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@IT
//@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ApplicationRunner.class)

@ComponentScan(basePackages = "comm")
public class CompanyServiceIT {
        private static final Integer COMPANY_ID = 1;

        @Autowired
        private CompanyService companyService;

        @Autowired
        private DatabaseProperties databaseProperties;

        @Test
        void findById() {
//            var actualResult = companyService.findById(COMPANY_ID);
//
//            assertTrue(actualResult.isPresent());
//
//            var expectedResult = new CompanyReadDto(COMPANY_ID);
//
//            actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
        }
    }
