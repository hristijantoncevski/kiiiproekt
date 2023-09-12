package mk.ukim.finki.kiiiproekt.service;


import mk.ukim.finki.kiiiproekt.model.Voter;

public interface AuthService {
    Voter login(String username, String password);
}

