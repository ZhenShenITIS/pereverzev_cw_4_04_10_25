package server.services;

import server.dto.UserRegistrationDto;
import server.entities.User;

public interface SignUpService {
    boolean signUp (UserRegistrationDto userRegistrationDto);
}
