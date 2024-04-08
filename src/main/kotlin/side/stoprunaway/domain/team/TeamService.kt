package side.stoprunaway.domain.team

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import side.stoprunaway.domain.member.MemberRepository

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun selectTeam(teamId: Long, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        val team = teamRepository.findById(teamId).get()
        member.updateTeam(team = team)
    }
}