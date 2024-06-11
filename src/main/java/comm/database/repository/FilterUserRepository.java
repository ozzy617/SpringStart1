package comm.database.repository;

import comm.database.entity.User;
import comm.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

}
