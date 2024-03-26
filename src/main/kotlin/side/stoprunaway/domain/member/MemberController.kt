package side.stoprunaway.domain.member

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import side.stoprunaway.common.RequiredAuth

@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/v1/members/sign-in")
    fun signIn(@RequestBody request: Request.SignIn): Response.SignIn {
        return Response.SignIn(memberService.signIn(request.name))
    }

    @RequiredAuth
    @GetMapping("/v1/members/profile")
    fun getProfile(metadata: HttpServletRequest): Response.GetProfile {
        val (name, weekNumber) = memberService.getProfile((metadata.getAttribute("identifier") as String).toLong())
        return Response.GetProfile(
            name, weekNumber
        )
    }

    @RequiredAuth
    @PostMapping("/v1/members/progress/complete")
    fun completeProcess(metadata: HttpServletRequest) {
        memberService.completeProcess((metadata.getAttribute("identifier") as String).toLong())
    }
}

class Request {
    data class SignIn(
        val name: String
    )
}

class Response {
    data class SignIn(
        val accessToken: String?,
    )

    data class GetProfile(
        val name: String,
        val weekNumber: Int,
    )
}