package mk.ukim.finki.kiiiproekt.service;

import mk.ukim.finki.kiiiproekt.model.Role;
import mk.ukim.finki.kiiiproekt.model.Voter;
import mk.ukim.finki.kiiiproekt.model.exceptions.UsernameWithThatCnAlreadyExists;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface VoterService extends UserDetailsService {
    Voter findById(Long id);
    Voter create(String name, String username, String password, Role role);
}
