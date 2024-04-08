package side.stoprunaway.domain.member

import jakarta.persistence.*
import side.stoprunaway.domain.Base
import side.stoprunaway.domain.team.Team

@Entity
class Member(
    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var name: String = "",

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.EAGER)
    var team: Team? = null,

    @JoinColumn(name = "learning_process_id")
    @OneToOne(fetch = FetchType.EAGER)
    var learningProcess: LearningProcess? = null,
): Base() {
}