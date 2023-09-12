package mk.ukim.finki.kiiiproekt.repository;

import mk.ukim.finki.kiiiproekt.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Long> {
    Optional<Voter> findByUsername(String username);
    Optional<Voter> findByUsernameAndPassword(String username, String password);
}
