package side.stoprunaway.domain.team

import org.springframework.stereotype.Service
import side.stoprunaway.domain.member.MemberRepository

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository,
) {

    fun selectTeam(teamId: Long, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        val team = teamRepository.findById(teamId).get()
        member.updateTeam(team = team)
    }
}