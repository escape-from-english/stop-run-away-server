package side.stoprunaway.common

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.*

class JwtUtils {
    companion object {
        private val key = Keys.hmacShaKeyFor("dGhpc2lzanVzdGFuZXhhbXBsZWtleWZvcnlvdVRoaXNpc2Fub3RoZXJleGFtcGxla2V5b3VtaWdodHVzZQ==".toByteArray())

        fun generateToken(identifier: Long): String {
            val now = Date()
            val expiryDate = Date(now.time + 259200)

            return Jwts.builder()
                .setSubject(identifier.toString())
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact()
        }

        fun validateTokenAndGetClaims(token: String): Claims {
            return try {
                Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .body
            } catch (e: Exception) {
                throw IllegalStateException("Invalid Token. ${e.message}")
            }
        }
    }
}