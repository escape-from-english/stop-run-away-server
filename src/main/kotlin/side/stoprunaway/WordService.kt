package side.stoprunaway

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class WordService(
    private val wordRepository: WordRepository,
) {

    @Transactional
    fun addWord(words: List<String>) {
        words.filterNot { wordRepository.existsByNameAndTodayDate(it) }
            .map { Word.make(it) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun addWordExcel(excelFile: MultipartFile) {
        ExcelUtils.readFirstColumn(excelFile)
            .filterNot { wordRepository.existsByNameAndTodayDate(it) }
            .map { Word.make(it) }
            .forEach { wordRepository.save(it) }
    }

    @Transactional
    fun getNotSolvedRandomWord(): String {
        val pickedWord = wordRepository.findNotSolvedWordsByTodayDate().randomOrNull()
        pickedWord?.submit()
        return pickedWord?.name ?: ""
    }

    fun isWordsNotSolvedExistence(): Boolean {
        return wordRepository.existsNotSolvedWordsByTodayDate()
    }
}