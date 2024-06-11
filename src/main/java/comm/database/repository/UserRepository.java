package comm.database.repository;

import comm.database.entity.QCompany;
import comm.database.entity.Role;
import comm.database.entity.User;
import comm.database.pool.ConnectionPool;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
import comm.dto.IPersonalInfo;
import comm.dto.PersonalInfo;
import comm.dto.UserFilter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Repository
//@ToString
public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository, QuerydslPredicateExecutor<User> {

    // 1 ВАРИАНТ - List<User> findAllBy(Pageable pageable);
    //2 ВАРИАНТ (В СТРУКТУРЕ ЕСТЬ ИТЕРАТОР) Slice<User> findAllBy(Pageable pageable);

    Optional<User> findByUsername(String username);
    Page<User> findAllBy(Pageable pageable); //3 ВАРИАНТ ЕСТЬ ДОП МЕТОДЫ

    Optional<User> findFirstByCompanyIsNotNullOrderByIdDesc();

    List<User> findFirst2By(Sort sort);
    @Query("select u from User u " +
            "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username" ,
        nativeQuery = true)
    List<User> findAllByUsername(String username);

//    List<User> findAllByFilter(UserFilter filter);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> tClass);
        @Query(value = "select u.firstname, u.lastname, u.birth_date from users u where company_id = :companyId",
        nativeQuery = true)
        List<IPersonalInfo> findAllByCompanyId(Integer companyId);





    //@Autowired
    //@Qualifier("connectionPool")
//    private final ConnectionPool connectionPool;
//    private Integer poolSize;
//    private List<ConnectionPool> connectionPools;

//    public UserRepository(ConnectionPool connectionPool) {
////                          @Value("${db.pool.size}") Integer poolSize,
////                          List<ConnectionPool> connectionPool) {
//        this.connectionPool = connectionPool;
////        this.poolSize = poolSize;
////        this.connectionPools = connectionPool;
//    }
    //    private String username;
//    private int poolSize;
//    private List<Object> args;
//    private Map<String, Object> properties;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("init userRepository");
//    }
//
//    @PreDestroy
//    public void destroy() {
//        System.out.println("destroy userRepository");
//    }
}
