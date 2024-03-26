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
    fun addWord(words: List<Model.Word>, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        words.filterNot { wordRepository.existsByNameAndWeekNumber(it.name, member.learningProcess!!.weekNumber) }
            .map { Word.make(it.name, it.meaning, member) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun addWordExcel(excelFile: MultipartFile, identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        ExcelUtils.readFirstColumn(excelFile)
            .filterNot { wordRepository.existsByNameAndWeekNumber(it.name, member.learningProcess!!.weekNumber) }
            .map { Word.make(it.name, it.meaning, member) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun getNotSolvedRandomWord(): Model.Word? {
        val pickedWord = wordRepository.findAllByStatus(WordStatus.NOT_SOLVED).randomOrNull()
        pickedWord?.submit()
        return pickedWord?.let { Model.Word(it.name, it.meaning, it.status) }
    }

    fun isWordsNotSolvedExistence(): Boolean {
        return wordRepository.existsByStatus(WordStatus.NOT_SOLVED)
    }

    fun getWords(identifier: Long): List<Model.Word> {
        return wordRepository.findAllByMemberIdAndStatus(identifier, WordStatus.NOT_SOLVED)
            .map { Model.Word(
                it.name,
                it.meaning,
                it.status
            ) }
    }

    fun getWordsByWeek(weekNumber: Int, identifier: Long): List<Model.Word> {
        return wordRepository.findAllByMemberIdAndWeekNumber(identifier, weekNumber)
            .map { Model.Word(
                it.name,
                it.meaning,
                it.status
            ) }
    }
}