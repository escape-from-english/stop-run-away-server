package side.stoprunaway.domain.word

import jakarta.persistence.*
import side.stoprunaway.domain.Base
import side.stoprunaway.domain.member.Member

@Entity
class Word(
    @Column(name = "word_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var name: String,

    var meaning: String,

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member,

    @Enumerated(value = EnumType.ORDINAL)
    var status: WordStatus,
): Base() {

    companion object {
        fun make(name: String, meaning: String, identifier: Long): Word {
            return Word(
                name = name,
                meaning = meaning,
                status = WordStatus.NOT_SOLVED,
                member = Member(id = identifier)
            )
        }
    }

    fun submit() { status = WordStatus.SOLVED
    }
}

enum class WordStatus {
    NOT_SOLVED, SOLVED
}