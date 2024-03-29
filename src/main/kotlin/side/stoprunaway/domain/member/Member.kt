package side.stoprunaway.domain.member

import jakarta.persistence.*
import side.stoprunaway.domain.Base

@Entity
class Member(
    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var name: String = "",

    @JoinColumn(name = "learning_process_id")
    @OneToOne(fetch = FetchType.EAGER)
    var learningProcess: LearningProcess? = null,
): Base() {
}