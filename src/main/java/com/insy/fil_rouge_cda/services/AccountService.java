package com.insy.fil_rouge_cda.services;


import com.insy.fil_rouge_cda.Dtos.AuthRequest;
import com.insy.fil_rouge_cda.Dtos.RegisterRequest;
import com.insy.fil_rouge_cda.models.Account;
import com.insy.fil_rouge_cda.models.Role;
import com.insy.fil_rouge_cda.repositories.IAccountRepository;
import com.insy.fil_rouge_cda.security.JwtService;
import com.insy.fil_rouge_cda.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final IAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest registerRequest) {
        Account accountToCreate = new Account();
        accountToCreate.setFirstName(registerRequest.getFirstName());
        accountToCreate.setLastName(registerRequest.getLastName());
        accountToCreate.setBirthday(registerRequest.getBirthday());
        accountToCreate.setUsername(registerRequest.getUsername());
        accountToCreate.setEmail(registerRequest.getEmail());


        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        accountToCreate.setPassword(encodedPassword);
        accountToCreate.setRole(Role.ROLE_USER);

        accountRepository.save(accountToCreate);
    }

    public String authenticate(AuthRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );



        authenticationManager.authenticate(authentication);
        Account accountFromDb = accountRepository.findByUsernameIgnoreCase(request.getUsername()).orElse(new Account());
        return jwtService.generateToken(accountFromDb);
    }

    public Account getCurrentAccount() {
        String username = SecurityUtils.getCurrentUsername();
        return accountRepository.findByUsernameIgnoreCase(username).orElse(null);
    }
}
