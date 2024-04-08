package side.stoprunaway.domain.word

import jakarta.persistence.*
import side.stoprunaway.domain.Base
import side.stoprunaway.domain.member.Member
import side.stoprunaway.domain.team.Team

@Entity
class Word(
    @Column(name = "word_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member,

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team,

    var weekNumber: Int,

    var name: String,

    var meaning: String,

    @Enumerated(value = EnumType.ORDINAL)
    var status: WordStatus,
): Base() {

    companion object {
        fun make(name: String, meaning: String, member: Member): Word {
            return Word(
                name = name,
                meaning = meaning,
                status = WordStatus.NOT_SOLVED,
                member = member,
                weekNumber = member.learningProcess!!.weekNumber,
                team = member.team!!
            )
        }
    }

    fun submit() { status = WordStatus.SOLVED
    }
}

enum class WordStatus {
    NOT_SOLVED, SOLVED
}