package vega.it.praksa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}
