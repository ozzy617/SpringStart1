package comm.integration.repository;

import comm.annotation.IT;
import comm.database.entity.Role;
import comm.database.repository.UserRepository;
import comm.dto.PersonalInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RunWith(SpringRunner.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void findAllByFirstnameContainingAndLastnameContainingTest(){
        var user = userRepository.findAllByFirstnameContainingAndLastnameContaining("a", "a");
        assertFalse(user.isEmpty());
        assertThat(user).hasSize(1);
    }

    @Test
    void updateRoleTest() {
        var entity1 = userRepository.findById(1L);
        var result = userRepository.updateRole(Role.USER, 1L, 2L);
        assertEquals(2, result);
        var entity2 = userRepository.findById(1L);
        assertEquals(Role.USER, entity2.get().getRole());
    }

    @Test
    void chekProjectionsTest() {
        var users = userRepository.findAllByCompanyId(6);
        assertThat(users).hasSize(2);
    }

    @Test
    void findFirstByCompanyIsNotNullOrderByIdDescTest() {
        var user = userRepository.findFirstByCompanyIsNotNullOrderByIdDesc();
        assertThat(user).isPresent();
        user.ifPresent(u -> assertEquals("Vasya", u.getFirstname()));
    }
    @Test
    void findFirstBy2Test() {
        var users = userRepository.findFirst2By(Sort.by("id").and(Sort.by("lastname")));
        assertThat(users).hasSize(2);
    }

    @Test
    void chekPagination() {
        var pegaeble = PageRequest.of(1, 1, Sort.by("id"));
        var users = userRepository.findAllBy(pegaeble);
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(1);
    }

    @Test
    void chekPaginationSlice() {
        var pegaeble = PageRequest.of(1, 1, Sort.by("id"));
        var slice = userRepository.findAllBy(pegaeble);
        slice.forEach(u -> System.out.println(u.getId()));
        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(u -> System.out.println(u.getId()));
        }
    }
    @Test
    void chekPaginationPage() {
        var pegaeble = PageRequest.of(1, 1, Sort.by("id"));
        var page = userRepository.findAllBy(pegaeble);
        page.forEach(u -> System.out.println(u.getId()));
        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(u -> System.out.println(u.getId()));
        }
        System.out.println(page.getTotalPages());
    }
}
