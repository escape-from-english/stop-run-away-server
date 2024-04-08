package side.stoprunaway.domain.team

import jakarta.persistence.*
import side.stoprunaway.domain.Base

@Entity
class Team(
    @Column(name = "team_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var name: String = "",
): Base()
