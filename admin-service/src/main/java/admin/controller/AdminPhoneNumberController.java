package admin.controller;


import admin.dto.AdminPhoneNumberDTO;
import admin.service.interfaces.AdminPhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins/{adminId}/phone-numbers")
public class AdminPhoneNumberController {

    @Autowired
    private AdminPhoneNumberService adminPhoneNumberService;

    // Listar todos os números de telefone de um admin
    @GetMapping
    public ResponseEntity<List<AdminPhoneNumberDTO>> getPhoneNumbersByAdmin(@PathVariable Long adminId) {
        List<AdminPhoneNumberDTO> phoneNumbers = adminPhoneNumberService.getPhoneNumbersByAdmin(adminId);
        return ResponseEntity.ok(phoneNumbers);
    }

    // Adicionar um novo número de telefone ao admin
    @PostMapping
    public ResponseEntity<AdminPhoneNumberDTO> addPhoneNumber(@PathVariable Long adminId, @RequestBody AdminPhoneNumberDTO phoneNumberDTO) {
        AdminPhoneNumberDTO newPhoneNumber = adminPhoneNumberService.addPhoneNumber(adminId, phoneNumberDTO);
        return ResponseEntity.ok(newPhoneNumber);
    }

    // Remover um número de telefone
    @DeleteMapping("/{phoneNumberId}")
    public ResponseEntity<Void> removePhoneNumber(@PathVariable Long adminId, @PathVariable Long phoneNumberId) {
        adminPhoneNumberService.removePhoneNumber(adminId, phoneNumberId);
        return ResponseEntity.noContent().build();
    }
}

