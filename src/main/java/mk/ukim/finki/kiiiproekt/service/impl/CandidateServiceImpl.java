package mk.ukim.finki.kiiiproekt.service.impl;

import mk.ukim.finki.kiiiproekt.model.Candidate;
import mk.ukim.finki.kiiiproekt.model.exceptions.InvalidCandidateIdException;
import mk.ukim.finki.kiiiproekt.model.exceptions.InvalidElectionIdException;
import mk.ukim.finki.kiiiproekt.repository.CandidateRepository;
import mk.ukim.finki.kiiiproekt.service.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;

    }

    @Override
    public List<Candidate> listAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
    }

    @Override
    public Candidate create(String name, String party, Long numberOfVotes) {
        return candidateRepository.save(new Candidate(name,party,numberOfVotes));
    }

    @Override
    public Candidate update(Long id, String name, String party, Long numberOfVotes) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
        candidate.setName(name);
        candidate.setParty(party);
        candidate.setNumberOfVotes(numberOfVotes);
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate deleteById(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
        candidateRepository.delete(candidate);
        return candidate;
    }
}
