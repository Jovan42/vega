package vega.it.praksa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamMemberListDto {
    private List<TeamMemberDto> teamMembers;
}