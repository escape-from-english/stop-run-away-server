package side.stoprunaway.common

import side.stoprunaway.domain.word.WordStatus

class Model {
    data class Word(
        val name: String,
        val meaning: String,
        val status: WordStatus?,
    )

    data class ExcelWord(
        val name: String,
        val meaning: String,
    )

    data class Member(
        val id: Long,
        val name: String,
    )

    data class TeamMember(
        val id: Long,
        val name: String,
        val members: List<Member>
    )
}