package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.services.TeamMemberService;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController extends GenericCrudControllerImpl<TeamMemberDto, TeamMemberListDto, Long,
        TeamMemberService> {
    @Autowired
    public TeamMemberController(TeamMemberService teamMemberService ) {
        super(teamMemberService );
    }

    @PostMapping("/login")
    ResponseEntity<Boolean> login(@RequestBody TeamMember teamMember) {
        return new ResponseEntity<>(service.login(teamMember.getUsername(), teamMember.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/logout")
    ResponseEntity<Void> logout(){
        service.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
