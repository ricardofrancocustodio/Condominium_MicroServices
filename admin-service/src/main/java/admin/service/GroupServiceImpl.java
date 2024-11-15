package admin.service;

import admin.dto.AdminDTO;
import admin.dto.AdminPhoneNumberDTO;
import admin.dto.GroupDTO;
import admin.dto.MemberDTO;
import admin.model.AdminModel;
import admin.model.AdminPhoneNumberModel;
import admin.model.GroupModel;
import admin.model.MemberModel;
import admin.repository.interfaces.GroupRepository;
import admin.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTO getGroupById(Long id) {
        GroupModel group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return convertToDTO(group);
    }

    @Override
    public GroupDTO createGroup(GroupDTO groupDTO) {
        GroupModel group = convertToEntity(groupDTO);
        GroupModel savedGroup = groupRepository.save(group);
        return convertToDTO(savedGroup);
    }

    @Override
    public GroupDTO updateGroup(Long id, GroupDTO groupDTO) {
        GroupModel group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Atualizar os dados do grupo
        group.setName(groupDTO.getName());
        group.setDescription(groupDTO.getDescription());
        groupRepository.save(group);

        return convertToDTO(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    // Métodos para converter entre Group e GroupDTO
    // Conversão de GroupModel para GroupDTO
    private GroupDTO convertToDTO(GroupModel group) {
        return GroupDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .platform(group.getPlatform())
                .status(group.getStatus())
                .owner(convertAdminToDTO(group.getOwner())) // Conversão do admin que é o dono do grupo
                .members(group.getMembers().stream()
                        .map(this::convertMemberToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    // Conversão de GroupDTO para GroupModel
    private GroupModel convertToEntity(GroupDTO groupDTO) {
        return GroupModel.builder()
                .name(groupDTO.getName())
                .description(groupDTO.getDescription())
                .platform(groupDTO.getPlatform())
                .status(groupDTO.getStatus())
                .owner(convertAdminToEntity(groupDTO.getOwner())) // Conversão do admin que é o dono do grupo
                .members(groupDTO.getMembers().stream()
                        .map(this::convertMemberToEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    // Conversão de MemberModel para MemberDTO
    private MemberDTO convertMemberToDTO(MemberModel member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .status(member.getStatus())
                .build();
    }

    // Conversão de MemberDTO para MemberModel
    private MemberModel convertMemberToEntity(MemberDTO memberDTO) {
        return MemberModel.builder()
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .status(memberDTO.getStatus())
                .build();
    }

    // Conversão de AdminModel para AdminDTO (caso o Group tenha um dono admin)
    private AdminDTO convertAdminToDTO(AdminModel admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .phoneNumbers(admin.getPhoneNumbers().stream()
                        .map(AdminPhoneNumberModel::getPhoneNumber)  // Extraímos apenas o número de telefone como String
                        .collect(Collectors.toList()))  // Coletamos como uma lista de Strings
                .build();
    }


    // Conversão de AdminDTO para AdminModel
    private AdminModel convertAdminToEntity(AdminDTO adminDTO) {
        AdminModel admin = AdminModel.builder()
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .password(adminDTO.getPassword()) // lembre-se de hashear a senha adequadamente
                .build();

        // Convertendo Strings (números de telefone) para AdminPhoneNumberModel
        List<AdminPhoneNumberModel> phoneNumbers = adminDTO.getPhoneNumbers().stream()
                .map(phoneNumber -> AdminPhoneNumberModel.builder()
                        .phoneNumber(phoneNumber)  // Usamos a String como o número de telefone
                        .adminModel(admin)  // Relacionando ao admin criado
                        .build())
                .collect(Collectors.toList());

        admin.setPhoneNumbers(phoneNumbers);  // Definimos a lista de AdminPhoneNumberModel no AdminModel
        return admin;
    }

}

