package vega.it.praksa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.services.TeamMemberService;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController extends GenericCrudControllerImpl<TeamMemberDto, TeamMemberListDto, Long,
        TeamMemberService> {
    public TeamMemberController(TeamMemberService teamMemberService ) {
        super(teamMemberService );
    }
}
