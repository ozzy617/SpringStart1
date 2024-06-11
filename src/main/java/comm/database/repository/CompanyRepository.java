package comm.database.repository;

import comm.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("select c from Company c " +
            "join fetch c.locales cl " +
            "where c.name = :name"
    )
    Optional<Company> findByName(String name);

    //Optional<Company> findById(Integer id);
    List<Company> findAllByNameContainingIgnoreCase(String fragment);
//    public Optional<Company> findById(Integer id) {
//        System.out.println("CompanyRepository findById method");
//        return Optional.of(new Company(id, null, Collections.emptyMap()));
//    }
}
