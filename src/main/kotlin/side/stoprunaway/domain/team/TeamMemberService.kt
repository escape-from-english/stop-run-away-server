package side.stoprunaway.domain.team

import org.springframework.stereotype.Service
import side.stoprunaway.common.Model
import side.stoprunaway.domain.member.MemberRepository

@Service
class TeamMemberService(
    private val teamMemberRepository: TeamMemberRepository,
    private val memberRepository: MemberRepository,
) {

    fun findTeams(identifier: Long): List<Model.TeamMember> {
        val member = memberRepository.findById(identifier).get()
        val teamMembers = teamMemberRepository.findAllByMember(member)
        return teamMembers.map {
            val team = it.team
            val innerTeamMembers = teamMemberRepository.findAllByTeam(it.team)
            Model.TeamMember(
                id = team.id,
                name = team.name,
                members = innerTeamMembers.map { innerTeamMember ->  Model.Member(
                        id = innerTeamMember.member.id,
                        name = innerTeamMember.member.name
                    )
                }
            )
        }
    }
}