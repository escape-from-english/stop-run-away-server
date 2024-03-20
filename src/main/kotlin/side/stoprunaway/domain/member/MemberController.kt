package side.stoprunaway.domain.member

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/v1/member/sign-in")
    fun signIn(@RequestBody request: Request.SignIn): Response.SignIn {
        return Response.SignIn(memberService.signIn(request.name))
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
}