package side.stoprunaway.common

import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class RequiredAuthAspect(
    private val request: HttpServletRequest,
) {

    @Pointcut("@annotation(RequiredAuth)")
    fun requiredAuth() {}

    @Before("requiredAuth()")
    fun before(jointPoint: JoinPoint) {
        val token = request.getHeader("Authorization")?.substring("Bearer ".length)
        if (token != null) {
            val claims = JwtUtils.validateTokenAndGetClaims(token)
            request.setAttribute("identifier", claims.subject)
        } else {
            throw IllegalStateException("JWT Token is required")
        }
    }
}