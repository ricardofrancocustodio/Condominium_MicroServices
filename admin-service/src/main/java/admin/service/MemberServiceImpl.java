package admin.service;

import admin.dto.MemberDTO;
import admin.model.GroupModel;
import admin.model.MemberModel;
import admin.repository.interfaces.GroupRepository;
import admin.repository.interfaces.MemberRepository;
import admin.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<MemberDTO> getMembersByGroup(Long groupId) {
        return memberRepository.findByGroupId(groupId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberById(Long groupId, Long memberId) {
        MemberModel member = memberRepository.findByIdAndGroupId(memberId, groupId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return convertToDTO(member);
    }

    @Override
    public MemberDTO addMemberToGroup(Long groupId, MemberDTO memberDTO) {
        MemberModel member = convertToEntity(memberDTO);

        // Buscar o GroupModel pelo groupId
        GroupModel group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Vincular o membro ao grupo
        member.setGroupModel(group);

        // Salvar o membro no repositório
        MemberModel savedMember = memberRepository.save(member);
        return convertToDTO(savedMember);
    }


    @Override
    public void removeMemberFromGroup(Long groupId, Long memberId) {
        memberRepository.deleteByIdAndGroupId(memberId, groupId);
    }

    // Conversão de MemberModel para MemberDTO
    private MemberDTO convertToDTO(MemberModel member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .status(member.getStatus())
                .build();
    }

    // Conversão de MemberDTO para MemberModel
    private MemberModel convertToEntity(MemberDTO memberDTO) {
        return MemberModel.builder()
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .status(memberDTO.getStatus())
                .build();
    }
}

