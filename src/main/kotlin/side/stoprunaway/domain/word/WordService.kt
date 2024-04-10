package side.stoprunaway.domain.word

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import side.stoprunaway.common.ExcelUtils
import side.stoprunaway.common.Model
import side.stoprunaway.domain.member.MemberRepository

@Service
class WordService(
    private val wordRepository: WordRepository,
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun addWords(words: List<Model.Word>, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        words.filterNot { wordRepository.existsByNameAndWeekNumberAndMember(it.name, member.learningProcess!!.weekNumber, member) }
            .map { Word.make(it.name, it.meaning, member) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun addWords(excelFile: MultipartFile, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        ExcelUtils.readFirstColumn(excelFile)
            .filterNot { wordRepository.existsByNameAndWeekNumberAndMember(it.name, member.learningProcess!!.weekNumber, member) }
            .map { Word.make(it.name, it.meaning, member) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun getNotSolvedRandomWord(identifier: Long): Model.Word? {
        val member = memberRepository.findById(identifier).get()
        val pickedWord = wordRepository.findAllByStatusAndTeam(WordStatus.NOT_SOLVED, member.team!!).randomOrNull() ?: return null
        val duplicatedPickedWords = wordRepository.findAllByNameAndTeam(pickedWord.name, member.team!!)
        duplicatedPickedWords.forEach { it.submit() }
        return pickedWord.let { Model.Word(it.name, it.meaning, it.status) }
    }

    fun isWordsNotSolvedExistence(identifier: Long): Boolean {
        val member = memberRepository.findById(identifier).get()
        return wordRepository.existsByStatusAndTeam(WordStatus.NOT_SOLVED, member.team!!)
    }

    fun getWords(identifier: Long): List<Model.Word> {
        val member = memberRepository.findById(identifier).get()
        return wordRepository.findAllByMemberIdAndStatusAndTeam(identifier, WordStatus.NOT_SOLVED, member.team!!)
            .map { Model.Word(
                it.name,
                it.meaning,
                it.status
            ) }
    }

    fun getWordsByWeek(weekNumber: Int, identifier: Long): List<Model.Word> {
        val member = memberRepository.findById(identifier).get()
        return wordRepository.findAllByMemberIdAndWeekNumberAndTeam(identifier, weekNumber, member.team!!)
            .map { Model.Word(
                it.name,
                it.meaning,
                it.status
            ) }
    }

    fun getRegisteredWordsCount(weekNumber: Int, identifier: Long): Int {
        val member = memberRepository.findById(identifier).get()
        return wordRepository.countWordsByWeekNumberAndTeam(weekNumber, member.team!!)
    }
}