package side.stoprunaway.domain.member

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import side.stoprunaway.common.JwtUtils

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    fun signIn(name: String): String? {
        val member = memberRepository.findByName(name) ?: return null
        return JwtUtils.generateToken(member.id)
    }

    fun getProfile(identifier: Long): Pair<String, Int> {
        val member = memberRepository.findById(identifier).get()
        return Pair(
            member.name,
            member.learningProcess!!.weekNumber
        )
    }

    @Transactional
    fun completeProcess(identifier: Long) {
        val member = memberRepository.findById(identifier).get()
        member.learningProcess!!.nextWeek()
    }
}