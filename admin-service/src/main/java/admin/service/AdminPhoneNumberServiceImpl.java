package admin.service;

import admin.dto.AdminPhoneNumberDTO;
import admin.model.AdminPhoneNumberModel;
import admin.repository.interfaces.AdminPhoneNumberRepository;
import admin.service.interfaces.AdminPhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminPhoneNumberServiceImpl implements AdminPhoneNumberService {

    @Autowired
    private AdminPhoneNumberRepository phoneNumberRepository;

    @Override
    public List<AdminPhoneNumberDTO> getPhoneNumbersByAdmin(Long adminId) {
        return phoneNumberRepository.findByAdminModel_Id(adminId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminPhoneNumberDTO addPhoneNumber(Long adminId, AdminPhoneNumberDTO phoneNumberDTO) {
        AdminPhoneNumberModel phoneNumber = convertToEntity(phoneNumberDTO);
        // Configura o admin ao qual o número de telefone está associado
        // Presumindo que você tenha o método setAdmin no AdminPhoneNumberModel
        phoneNumber.setAdminId(adminId);
        AdminPhoneNumberModel savedPhoneNumber = phoneNumberRepository.save(phoneNumber);
        return convertToDTO(savedPhoneNumber);
    }

    @Override
    public void removePhoneNumber(Long adminId, Long phoneNumberId) {

    }

    // Método para converter AdminPhoneNumberModel para AdminPhoneNumberDTO
    private AdminPhoneNumberDTO convertToDTO(AdminPhoneNumberModel phoneNumberModel) {
        return AdminPhoneNumberDTO.builder()
                .id(phoneNumberModel.getId())
                .phoneNumber(phoneNumberModel.getPhoneNumber())
                .build();
    }

    // Método para converter AdminPhoneNumberDTO para AdminPhoneNumberModel
    private AdminPhoneNumberModel convertToEntity(AdminPhoneNumberDTO phoneNumberDTO) {
        return AdminPhoneNumberModel.builder()
                .phoneNumber(phoneNumberDTO.getPhoneNumber())
                .build();
    }


}