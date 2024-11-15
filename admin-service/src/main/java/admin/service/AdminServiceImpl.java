package admin.service;

import admin.dto.AdminDTO;
import admin.dto.AdminPhoneNumberDTO;
import admin.model.AdminModel;
import admin.model.AdminPhoneNumberModel;
import admin.repository.interfaces.AdminRepository;
import admin.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {


        @Autowired
        private AdminRepository adminRepository;

        @Override
        public List<AdminDTO> getAllAdmins() {
            return adminRepository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        @Override
        public AdminDTO getAdminById(Long id) {
            AdminModel admin = adminRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            return convertToDTO(admin);
        }

        @Override
        public AdminDTO createAdmin(AdminDTO adminDTO) {
            AdminModel admin = convertToEntity(adminDTO);
            AdminModel savedAdmin = adminRepository.save(admin);
            return convertToDTO(savedAdmin);
        }

        @Override
        public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
            AdminModel admin = adminRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));

            // Atualizar os dados do admin
            admin.setName(adminDTO.getName());
            admin.setEmail(adminDTO.getEmail());
            adminRepository.save(admin);

            return convertToDTO(admin);
        }

        @Override
        public void deleteAdmin(Long id) {
            adminRepository.deleteById(id);
        }


    // Conversão de AdminModel para AdminDTO
    private AdminDTO convertToDTO(AdminModel admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .createdAt(admin.getCreatedAt())
                .phoneNumbers(admin.getPhoneNumbers().stream()
                        .map(AdminPhoneNumberModel::getPhoneNumber) // Obtenha apenas o número de telefone como String
                        .collect(Collectors.toList())) // Coleta a lista de Strings
                .build();
    }


    // Conversão de AdminDTO para AdminModel
    private AdminModel convertToEntity(AdminDTO adminDTO) {
        AdminModel admin = AdminModel.builder()
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .password(adminDTO.getPassword()) // lembre-se de hashear a senha adequadamente
                .build();

        // Convertendo a lista de Strings (números de telefone) para AdminPhoneNumberModel
        List<AdminPhoneNumberModel> phoneNumbers = adminDTO.getPhoneNumbers().stream()
                .map(phone -> AdminPhoneNumberModel.builder()
                        .phoneNumber(phone)   // O número de telefone vem da String
                        .adminModel(admin)         // Relacionando ao admin criado
                        .build())
                .collect(Collectors.toList());

        admin.setPhoneNumbers(phoneNumbers); // Definir a lista de AdminPhoneNumberModel no AdminModel
        return admin;
    }


    // Conversão de AdminPhoneNumberModel para AdminPhoneNumberDTO
    private AdminPhoneNumberDTO convertPhoneNumberToDTO(AdminPhoneNumberModel phoneNumber) {
        return AdminPhoneNumberDTO.builder()
                .id(phoneNumber.getId())
                .phoneNumber(phoneNumber.getPhoneNumber())
                .build();
    }

    // Conversão de AdminPhoneNumberDTO para AdminPhoneNumberModel
    private AdminPhoneNumberModel convertPhoneNumberToEntity(AdminPhoneNumberDTO phoneNumberDTO) {
        return AdminPhoneNumberModel.builder()
                .phoneNumber(phoneNumberDTO.getPhoneNumber())
                .build();
    }

}
