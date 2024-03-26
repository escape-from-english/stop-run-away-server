package side.stoprunaway.domain.member

import jakarta.persistence.*
import side.stoprunaway.domain.Base

@Entity
class LearningProcess(
    @Column(name = "leaning_process_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var weekNumber: Int = 1,
): Base() {

    fun nextWeek() {
        weekNumber++
    }
}