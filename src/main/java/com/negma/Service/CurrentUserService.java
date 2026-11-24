package com.negma.Service;

import com.negma.Entity.User;
import com.negma.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    UserRepo userRepo;

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        User user = new User();

        if (authentication != null) {
            String email = authentication.getName();
            user = userRepo.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.UNAUTHORIZED, "Email not found"

                    ));

        }
        return user;

    }


}
