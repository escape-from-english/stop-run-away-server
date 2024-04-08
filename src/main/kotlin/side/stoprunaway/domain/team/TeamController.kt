package side.stoprunaway.domain.team

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import side.stoprunaway.common.Model
import side.stoprunaway.common.RequiredAuth

@RestController
class TeamController(
    private val teamMemberService: TeamMemberService,
) {

    @RequiredAuth
    @PostMapping("/v1/teams")
    fun findTeams(metadata: HttpServletRequest): Response.FindTeams {
        return Response.FindTeams(teamMemberService.findTeams((metadata.getAttribute("identifier") as String).toLong()))
    }
}

class Response {
    data class FindTeams(
        val teams: List<Model.TeamMember>
    )
}