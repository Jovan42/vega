package vega.it.praksa.services;

import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;

public interface TeamMemberService extends GenericService<TeamMemberDto, TeamMemberListDto, Long>{
    TeamMemberDto changePassword(Long id, String newPassword);
    TeamMember get(String username);
    Boolean login(String username, String password);
    void logout();
}
