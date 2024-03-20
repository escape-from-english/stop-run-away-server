package side.stoprunaway.domain.word

import jakarta.persistence.*
import side.stoprunaway.domain.Base
import java.time.LocalDateTime

@Entity
class Word(
    @Column(name = "word_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var name: String,

    var meaning: String,

    @Enumerated(value = EnumType.ORDINAL)
    var status: WordStatus,
): Base() {

    companion object {
        fun make(name: String, meaning: String): Word {
            return Word(
                name = name,
                meaning = meaning,
                status = WordStatus.NOT_SOLVED,
            )
        }
    }

    fun submit() { status = WordStatus.SOLVED
    }
}

enum class WordStatus {
    NOT_SOLVED, SOLVED
}