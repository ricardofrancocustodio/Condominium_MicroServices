package admin.controller;

import admin.dto.MemberDTO;
import admin.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Listar todos os membros de um grupo
    @GetMapping
    public ResponseEntity<List<MemberDTO>> getMembersByGroup(@PathVariable Long groupId) {
        List<MemberDTO> members = memberService.getMembersByGroup(groupId);
        return ResponseEntity.ok(members);
    }

    // Obter um membro pelo ID
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long groupId, @PathVariable Long memberId) {
        MemberDTO member = memberService.getMemberById(groupId, memberId);
        return ResponseEntity.ok(member);
    }

    // Adicionar um novo membro ao grupo
    @PostMapping
    public ResponseEntity<MemberDTO> addMemberToGroup(@PathVariable Long groupId, @RequestBody MemberDTO memberDTO) {
        MemberDTO newMember = memberService.addMemberToGroup(groupId, memberDTO);
        return ResponseEntity.ok(newMember);
    }

    // Remover um membro de um grupo
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMemberFromGroup(@PathVariable Long groupId, @PathVariable Long memberId) {
        memberService.removeMemberFromGroup(groupId, memberId);
        return ResponseEntity.noContent().build();
    }
}

