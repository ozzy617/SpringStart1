package comm.mapper;

import comm.dto.UserDto;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ToString
public class UserMapper {
    @Autowired
    private UserDto userDto;

}
