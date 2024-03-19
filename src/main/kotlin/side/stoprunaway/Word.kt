package side.stoprunaway

import jakarta.persistence.*
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

    var createdAt: LocalDateTime? = LocalDateTime.now(),

    var createdBy: String? = "SYSTEM",

    var updatedAt: LocalDateTime = LocalDateTime.now(),

    var updatedBy: String = "SYSTEM",
) {

    companion object {
        fun make(name: String, meaning: String): Word {
            return Word(
                name = name,
                meaning = meaning,
                status = WordStatus.NOT_SOLVED,
            )
        }
    }

    fun submit() { status = WordStatus.SOLVED }
}

enum class WordStatus {
    NOT_SOLVED, SOLVED
}