package com.halks.distribution_erp.User;

import com.halks.distribution_erp.User.dto.UserSummary;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserSummary toResponse(User user) {
        return new UserSummary(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

}
