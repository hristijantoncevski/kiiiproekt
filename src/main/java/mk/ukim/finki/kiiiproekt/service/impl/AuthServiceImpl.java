package mk.ukim.finki.kiiiproekt.service.impl;

import mk.ukim.finki.kiiiproekt.model.Voter;
import mk.ukim.finki.kiiiproekt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.kiiiproekt.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.kiiiproekt.repository.VoterRepository;
import mk.ukim.finki.kiiiproekt.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final VoterRepository voterRepository;

    public AuthServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public Voter login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return voterRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
}