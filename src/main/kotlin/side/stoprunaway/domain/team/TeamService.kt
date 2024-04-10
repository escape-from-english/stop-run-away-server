package side.stoprunaway.domain.team

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import side.stoprunaway.common.KnownException
import side.stoprunaway.domain.member.MemberRepository

@Service
class TeamService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun selectTeam(teamId: Long, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        val team = teamRepository.findById(teamId).orElseThrow {
            KnownException("해당 ID : $teamId 팀은 존재하지 않습니다.")
        }
        member.updateTeam(team = team)
    }
}