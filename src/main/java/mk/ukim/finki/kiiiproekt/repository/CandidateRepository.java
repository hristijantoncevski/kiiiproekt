package mk.ukim.finki.kiiiproekt.repository;

import mk.ukim.finki.kiiiproekt.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

}
