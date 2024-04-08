package side.stoprunaway.domain.team

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import side.stoprunaway.common.Model
import side.stoprunaway.common.RequiredAuth

@RestController
class TeamController(
    private val teamMemberService: TeamMemberService,
    private val teamService: TeamService,
) {

    @RequiredAuth
    @GetMapping("/v1/teams")
    fun findTeams(metadata: HttpServletRequest): Response.FindTeams {
        return Response.FindTeams(teamMemberService.findTeams((metadata.getAttribute("identifier") as String).toLong()))
    }

    @RequiredAuth
    @PostMapping("/v1/teams")
    fun selectTeam(@RequestBody request: Request.SelectTeam, metadata: HttpServletRequest) {
        return teamService.selectTeam(request.teamId, (metadata.getAttribute("identifier") as String).toLong())
    }
}

class Request {
    data class SelectTeam(
        val teamId: Long,
    )
}

class Response {
    data class FindTeams(
        val teams: List<Model.TeamMember>
    )
}