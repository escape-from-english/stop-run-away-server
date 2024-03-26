package side.stoprunaway.domain.word

import org.springframework.data.jpa.repository.JpaRepository

interface WordRepository: JpaRepository<Word, Long> {

    fun existsByName(name: String): Boolean

    fun findAllByStatus(status: WordStatus): List<Word>

    fun existsByStatus(status: WordStatus): Boolean

    fun findAllByMemberIdAndStatus(memberId: Long, status: WordStatus): List<Word>

    fun findAllByMemberIdAndWeekNumber(memberId: Long, weekNumber: Int): List<Word>
}