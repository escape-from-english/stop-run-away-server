package side.stoprunaway.domain.member

import org.springframework.stereotype.Service
import side.stoprunaway.common.JwtUtils

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    fun signIn(name: String): String? {
        if (memberRepository.existsByName(name)) {
            return JwtUtils.generateToken(name)
        }
        return null
    }
}