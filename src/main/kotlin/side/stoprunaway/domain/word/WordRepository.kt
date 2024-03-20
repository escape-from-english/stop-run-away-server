package side.stoprunaway.domain.word

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface WordRepository: JpaRepository<Word, Long> {

    @Query("SELECT COUNT(w) > 0 FROM Word w WHERE w.name = :name AND FUNCTION('DATE', w.createdAt) = CURRENT_DATE")
    fun existsByNameAndTodayDate(name: String): Boolean

    @Query("SELECT w FROM Word w WHERE w.status = 0 AND FUNCTION('DATE', w.createdAt) = CURRENT_DATE")
    fun findNotSolvedWordsByTodayDate(): List<Word>

    @Query("SELECT COUNT(w) > 0 FROM Word w WHERE w.status = 0 AND FUNCTION('DATE', w.createdAt) = CURRENT_DATE")
    fun existsNotSolvedWordsByTodayDate(): Boolean
}