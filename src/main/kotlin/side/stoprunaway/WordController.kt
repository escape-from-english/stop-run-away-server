package side.stoprunaway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class WordController(
    private val wordService: WordService,
) {

    @PostMapping("/words")
    fun addWord(@RequestBody request: Request.AddWord) {
        wordService.addWord(request.words)
    }

    @PostMapping("/words/excels")
    fun addWordExcel(request: Request.AddWordExcel) {
        wordService.addWordExcel(request.excelFile)
    }

    @GetMapping("/words")
    fun getRandomWord(): String {
        return wordService.getRandomWord()
    }

    @GetMapping("/words/existence")
    fun isWordsExistence(): Boolean {
        return wordService.isWordsExistence()
    }
}

class Request {
    data class AddWord(
        val words: List<String>
    )

    data class AddWordExcel(
        val excelFile: MultipartFile
    )
}