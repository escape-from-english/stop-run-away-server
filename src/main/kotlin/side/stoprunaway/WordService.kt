package side.stoprunaway

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
    fun addWordExcel() {

    }

    fun getRandomWord() {

    }
}