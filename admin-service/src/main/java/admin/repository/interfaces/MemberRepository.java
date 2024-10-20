package admin.repository.interfaces;


import admin.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel, Long> {

    // Método personalizado para encontrar membros por grupo
    List<MemberModel> findByGroupId(Long groupId);

    // Remover membro de um grupo específico
    void deleteByIdAndGroupId(Long memberId, Long groupId);

    Optional<MemberModel> findByIdAndGroupId(Long memberId, Long groupId);
}
