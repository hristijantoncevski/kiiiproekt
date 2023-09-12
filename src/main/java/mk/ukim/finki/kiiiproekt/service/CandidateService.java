package mk.ukim.finki.kiiiproekt.service;

import mk.ukim.finki.kiiiproekt.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> listAll();
    Candidate findById(Long id);
    Candidate create(String name, String party, Long numberOfVotes);
    Candidate update(Long id, String name, String party, Long numberOfVotes);
    Candidate deleteById(Long id);
}
