package co.ogram.security

import io.smallrye.jwt.auth.principal.*
import org.jose4j.jwa.AlgorithmConstraints
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.JwtClaims
import org.jose4j.jwt.consumer.InvalidJwtException
import java.nio.charset.StandardCharsets
import java.util.*
import javax.annotation.Priority
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Alternative
import javax.ws.rs.ForbiddenException

//@ApplicationScoped
//@Alternative
//@Priority(1)
//class OgramJWTCallerPrincipalFactory : JWTCallerPrincipalFactory() {
//    @Throws(ParseException::class)
//    override fun parse(token: String, authContextInfo: JWTAuthContextInfo): JWTCallerPrincipal {
//        return try {
//            // Token has already been verified, parse the token claims only
//            val json = String(Base64.getUrlDecoder().decode(token.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
//                .toTypedArray()[1]), StandardCharsets.UTF_8)
//            val jwtClaims = JwtClaims.parse(json)
//            val userIdClaimValue = jwtClaims.getClaimValue("pid")
//            jwtClaims.setClaim("sub", userIdClaimValue.toString())
//            val role: String = when (jwtClaims.getClaimValue("type") as Long) {
//                1L -> Role.SP.name
//                2L -> {
//                    val roles = jwtClaims.getClaimValue("roles") as ArrayList<*>
//                    val role = if (roles.contains(1L)) {
//                        Role.CLIENT_OWNER.name
//                    } else if (roles.contains(2L)) {
//                        Role.CLIENT_ADMIN.name
//                    } else if (roles.contains(3L)) {
//                        Role.CLIENT_MANAGER.name
//                    } else if (roles.contains(4L)) {
//                        Role.CLIENT_FINANCE.name
//                    } else throw ForbiddenException()
//                    role
//                }
//                else -> throw ForbiddenException()
//            }
//            jwtClaims.setClaim("groups", listOf(role))
//            DefaultJWTCallerPrincipal(jwtClaims)
//        } catch (ex: InvalidJwtException) {
//            throw ParseException(ex.message)
//        }
//    }
//}