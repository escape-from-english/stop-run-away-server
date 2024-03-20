package side.stoprunaway.domain.member

import org.springframework.stereotype.Service
import side.stoprunaway.common.JwtUtils

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    fun signIn(name: String): String? {
        val member = memberRepository.findByName(name) ?: return null
        return JwtUtils.generateToken(member.id)
    }
}