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

    @PostMapping("/v1/words/texts")
    fun addWord(@RequestBody request: Request.AddWordText) {
        wordService.addWord(request.words)
    }

    @PostMapping("/v1/words/excels")
    fun addWordExcel(request: Request.AddWordExcel) {
        wordService.addWordExcel(request.excelFile)
    }

    @GetMapping("/v1/words/status/not-solved/random")
    fun getNotSolvedRandomWord(): Response.GetNotSolvedRandomWord {
        return Response.GetNotSolvedRandomWord(wordService.getNotSolvedRandomWord())
    }

    @GetMapping("/v1/words/status/not-solved/existence")
    fun isNotSolvedWordsExistence(): Boolean {
        return wordService.isWordsNotSolvedExistence()
    }
}

class Request {
    data class AddWordText(
        val words: List<Model.Word>
    )

    data class AddWordExcel(
        val excelFile: MultipartFile
    )
}

class Response {
    data class GetNotSolvedRandomWord(
        val word: Model.Word?
    )
}