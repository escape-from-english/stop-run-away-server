package side.stoprunaway.domain.word

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import side.stoprunaway.common.ExcelUtils
import side.stoprunaway.common.Model

@Service
class WordService(
    private val wordRepository: WordRepository,
) {

    @Transactional
    fun addWord(words: List<Model.Word>) {
        words.filterNot { wordRepository.existsByNameAndTodayDate(it.name) }
            .map { Word.make(it.name, it.meaning) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun addWordExcel(excelFile: MultipartFile) {
        ExcelUtils.readFirstColumn(excelFile)
            .filterNot { wordRepository.existsByNameAndTodayDate(it.name) }
            .map { Word.make(it.name, it.meaning) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun getNotSolvedRandomWord(): Model.Word? {
        val pickedWord = wordRepository.findNotSolvedWordsByTodayDate().randomOrNull()
        pickedWord?.submit()
        return pickedWord?.let { Model.Word(it.name, it.meaning) }
    }

    fun isWordsNotSolvedExistence(): Boolean {
        return wordRepository.existsNotSolvedWordsByTodayDate()
    }
}