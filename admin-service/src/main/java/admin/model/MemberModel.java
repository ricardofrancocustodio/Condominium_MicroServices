package admin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(name = "join_date", nullable = false, updatable = false)
    private LocalDateTime joinDate;

    @Column(nullable = false)
    private String status; // active, removed, etc.

    // Relacionamento com Group
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupModel groupModel;

    @PrePersist
    protected void onCreate() {
        joinDate = LocalDateTime.now();
    }

    // Getters e Setters
}
