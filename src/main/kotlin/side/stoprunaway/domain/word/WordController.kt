package side.stoprunaway.domain.word

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import side.stoprunaway.common.Model
import side.stoprunaway.common.RequiredAuth

@RestController
class WordController(
    private val wordService: WordService,
) {

    @RequiredAuth
    @PostMapping("/v1/words/texts")
    fun addWords(@RequestBody request: Request.AddWordText, metadata: HttpServletRequest) {
        wordService.addWords(request.words, (metadata.getAttribute("identifier") as String).toLong())
    }

    @RequiredAuth
    @PostMapping("/v1/words/excels")
    fun addWords(request: Request.AddWordExcel, metadata: HttpServletRequest) {
        wordService.addWords(request.excelFile, (metadata.getAttribute("identifier") as String).toLong())
    }

    @RequiredAuth
    @GetMapping("/v1/words/status/not-solved/random")
    fun getNotSolvedRandomWord(metadata: HttpServletRequest): Response.GetNotSolvedRandomWord {
        return Response.GetNotSolvedRandomWord(wordService.getNotSolvedRandomWord((metadata.getAttribute("identifier") as String).toLong()))
    }

    @RequiredAuth
    @GetMapping("/v1/words/status/not-solved/existence")
    fun isNotSolvedWordsExistence(metadata: HttpServletRequest): Boolean {
        return wordService.isWordsNotSolvedExistence((metadata.getAttribute("identifier") as String).toLong())
    }

    @RequiredAuth
    @GetMapping("/v1/words/status/not-solved")
    fun getWords(metadata: HttpServletRequest): Response.GetWords {
        return Response.GetWords(wordService.getWords((metadata.getAttribute("identifier") as String).toLong()))
    }

    @RequiredAuth
    @GetMapping("/v1/words/weeks/{weekNumber}")
    fun getWordsByWeekNumber(@PathVariable weekNumber: Int, metadata: HttpServletRequest): Response.GetWords {
        return Response.GetWords(wordService.getWordsByWeek(weekNumber, (metadata.getAttribute("identifier") as String).toLong()))
    }

    @RequiredAuth
    @GetMapping("/v1/words/weeks/{weekNumber}/count")
    fun getRegisteredWordsCount(@PathVariable weekNumber: Int, metadata: HttpServletRequest): Response.GetRegisteredWordsCount {
        return Response.GetRegisteredWordsCount(wordService.getRegisteredWordsCount(weekNumber, (metadata.getAttribute("identifier") as String).toLong()))
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

    data class GetWords(
        val words: List<Model.Word>
    )

    data class GetRegisteredWordsCount(
        val registeredWordsCount: Int,
    )
}