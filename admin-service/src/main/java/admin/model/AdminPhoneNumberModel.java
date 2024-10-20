package admin.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;

@Entity
@Table(name = "admin_phone_numbers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdminPhoneNumberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // Relacionamento com Admin
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminModel adminModel;

    private AdminModel admin;

    public void setAdminId(Long adminId) {
    }


}
