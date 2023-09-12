package mk.ukim.finki.kiiiproekt.web;

import mk.ukim.finki.kiiiproekt.model.Candidate;
import mk.ukim.finki.kiiiproekt.model.Voter;
import mk.ukim.finki.kiiiproekt.repository.CandidateRepository;
import mk.ukim.finki.kiiiproekt.repository.VoterRepository;
import mk.ukim.finki.kiiiproekt.service.CandidateService;
import mk.ukim.finki.kiiiproekt.service.VoterService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;

    private final VoterRepository voterRepository;

    public CandidateController(CandidateService candidateService, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.candidateService = candidateService;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    @GetMapping({"/election","/"})
    public String getElectionPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("candidates", this.candidateService.listAll());
        model.addAttribute("bodyContent", "elections");
        return "master-template";
    }

    @GetMapping("/election/add")
    public String showAddCandidate(Model model){
        model.addAttribute("bodyContent", "add-candidate");
        return "master-template";
    }

    @GetMapping("/election/edit/{id}")
    public String showEditCandidate(@PathVariable Long id,Model model) {
        Candidate candidate = this.candidateService.findById(id);

        model.addAttribute("candidate", candidate);
        model.addAttribute("bodyContent", "add-candidate");
        return "master-template";
    }

    @GetMapping("/election/results")
    public String getResults(Model model){
        model.addAttribute("candidates", this.candidateRepository.findAll(Sort.by(Sort.Direction.DESC,"numberOfVotes")));
        model.addAttribute("bodyContent", "results");
        return "master-template";
    }

    @PostMapping("/election")
    public String create(@RequestParam String name,@RequestParam String party){
        this.candidateService.create(name,party,0L);
        return "redirect:/election";
    }

    @PostMapping("/election/{id}")
    public String update(@PathVariable Long id,@RequestParam String name,@RequestParam String party){
        this.candidateService.update(id,name,party,0L);
        return "redirect:/election";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteElection(@PathVariable Long id){
        this.candidateService.deleteById(id);
        return "redirect:/election";
    }

    @PostMapping("/election/vote")
    public String vote(@RequestParam String candidateVote, Principal principal){
        String username = principal.getName();
        Voter voter = this.voterRepository.findByUsername(username).get();

        if(voter.getHasVoted()){
            return "redirect:/election/results";
        }

        if (!voter.getHasVoted()){
            Candidate candidate = this.candidateRepository.findById(Long.parseLong(candidateVote)).get();
            candidate.setNumberOfVotes(candidate.getNumberOfVotes()+1);
            this.candidateRepository.save(candidate);
            voter.setHasVoted(true);
            this.voterRepository.save(voter);

            return "redirect:/election/results";
        }
        return "redirect:/";
    }
}
