package side.stoprunaway.domain.word

import org.springframework.data.jpa.repository.JpaRepository
import side.stoprunaway.domain.team.Team

interface WordRepository: JpaRepository<Word, Long> {

    fun existsByNameAndWeekNumber(name: String, weekNumber: Int): Boolean

    fun findAllByNameAndTeam(name: String, team: Team): List<Word>

    fun findAllByStatusAndTeam(status: WordStatus, team: Team): List<Word>

    fun existsByStatusAndTeam(status: WordStatus, team: Team): Boolean

    fun findAllByMemberIdAndStatusAndTeam(memberId: Long, status: WordStatus, team: Team): List<Word>

    fun findAllByMemberIdAndWeekNumberAndTeam(memberId: Long, weekNumber: Int, team: Team): List<Word>
}