package side.stoprunaway.domain.team

import org.springframework.data.jpa.repository.JpaRepository
import side.stoprunaway.domain.member.Member

interface TeamMemberRepository: JpaRepository<TeamMember, Long> {

    fun findAllByMember(member: Member): List<TeamMember>

    fun findAllByTeam(team: Team): List<TeamMember>
}