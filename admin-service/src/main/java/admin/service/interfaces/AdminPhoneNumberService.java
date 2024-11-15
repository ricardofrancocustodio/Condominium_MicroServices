package admin.service.interfaces;


import admin.dto.AdminPhoneNumberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPhoneNumberService {
    List<AdminPhoneNumberDTO> getPhoneNumbersByAdmin(Long adminId);
    AdminPhoneNumberDTO addPhoneNumber(Long adminId, AdminPhoneNumberDTO phoneNumberDTO);
    void removePhoneNumber(Long adminId, Long phoneNumberId);
}

