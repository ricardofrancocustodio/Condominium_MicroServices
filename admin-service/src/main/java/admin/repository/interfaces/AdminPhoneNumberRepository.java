package admin.repository.interfaces;


import admin.model.AdminPhoneNumberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPhoneNumberRepository extends JpaRepository<AdminPhoneNumberModel, Long> {

    // Método personalizado para encontrar os números de telefone de um admin
   // List<AdminPhoneNumberModel> findByAdminId(Long adminId);
    List<AdminPhoneNumberModel> findByAdminModel_Id(Long adminId);
}
