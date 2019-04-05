package vega.it.praksa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.dtos.LoginDto;
import vega.it.praksa.services.TeamMemberService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    TeamMemberService teamMemberService;

    public AuthController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }



    @PostMapping("/login")
    ResponseEntity<LoginDto> login(@RequestBody TeamMember teamMember) {
        LoginDto loginDto = new LoginDto(teamMember.getUsername(),
                teamMemberService.login(teamMember.getUsername(), teamMember.getPassword()));
                return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    ResponseEntity<Void> logout(){
        teamMemberService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/loggedIn")
    ResponseEntity<String> loggedIn (){
        return new ResponseEntity<>(teamMemberService.getLoggedIn(), HttpStatus.OK);

    }
}
