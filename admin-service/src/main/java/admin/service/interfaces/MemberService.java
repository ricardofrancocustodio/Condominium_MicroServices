package admin.service.interfaces;


import admin.dto.MemberDTO;
import java.util.List;

public interface MemberService {
    List<MemberDTO> getMembersByGroup(Long groupId);
    MemberDTO getMemberById(Long groupId, Long memberId);
    MemberDTO addMemberToGroup(Long groupId, MemberDTO memberDTO);
    void removeMemberFromGroup(Long groupId, Long memberId);
}

