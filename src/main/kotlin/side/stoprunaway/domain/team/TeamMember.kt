package side.stoprunaway.domain.team

import jakarta.persistence.*
import side.stoprunaway.domain.Base
import side.stoprunaway.domain.member.Member

@Entity
class TeamMember(
    @Column(name = "team_member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team,

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member,
): Base()
