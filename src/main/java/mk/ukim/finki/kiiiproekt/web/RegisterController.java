package mk.ukim.finki.kiiiproekt.web;

import mk.ukim.finki.kiiiproekt.model.Role;
import mk.ukim.finki.kiiiproekt.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.kiiiproekt.model.exceptions.UsernameWithThatCnAlreadyExists;
import mk.ukim.finki.kiiiproekt.service.AuthService;
import mk.ukim.finki.kiiiproekt.service.VoterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import javax.security.auth.x500.X500Principal;
import javax.servlet.http.HttpServletRequest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final VoterService voterService;

    public RegisterController(AuthService authService, VoterService voterService) {
        this.authService = authService;
        this.voterService = voterService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping String register(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam Role role) {
        try{
            this.voterService.create(name,username,password,role);
            return "redirect:/login";
        } catch(InvalidArgumentsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
