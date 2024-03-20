package side.stoprunaway.domain

import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class Base(
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    var createdBy: String? = "SYSTEM",

    var updatedAt: LocalDateTime = LocalDateTime.now(),

    var updatedBy: String = "SYSTEM",
) {
}