package comm.integration.service;

import comm.annotation.IT;
import comm.database.entity.Role;
import comm.dto.UserCreateEditDto;
import comm.dto.UserReadDto;
import comm.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@IT
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
@ComponentScan(basePackages = "comm")
public class UserServiceTest {

    private final static Long USER_1 = 1L;
    private final static Integer COMPANY_1 = 6;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("alex9001", user.getUsername()));
    }
//
//    @Test
//    void createTest() {
//        UserCreateEditDto userDto = new UserCreateEditDto(
//             "test@gmail",
//                LocalDate.now(),
//                "Test",
//                "Test",
//                Role.ADMIN,
//                COMPANY_1
//        );
//
//        UserReadDto actualResult = userService.create(userDto);
//
//        assertEquals(userDto.getUsername(), actualResult.getUsername());
//        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
//        assertEquals(userDto.getLastname(), actualResult.getLastname());
//        //assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
//
//    }
//
//    @Test
//    void updateTest() {
//        UserCreateEditDto userDto = new UserCreateEditDto(
//                "test@gmail",
//                LocalDate.now(),
//                "Test",
//                "Test",
//                Role.ADMIN,
//                COMPANY_1
//        );
//
//        Optional<UserReadDto> actualRes = userService.update(USER_1, userDto);
//        actualRes.ifPresent(user -> {
//            assertEquals(userDto.getUsername(), user.getUsername());
//            assertEquals(userDto.getLastname(), user.getLastname());
//            assertEquals(userDto.getCompanyId(), user.getCompany().id());
//        });
//    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_1));
    }
}
