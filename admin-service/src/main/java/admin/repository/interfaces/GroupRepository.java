package admin.repository.interfaces;

import admin.model.GroupModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {

    // Se necessário, você pode adicionar métodos personalizados, por exemplo:
    List<GroupModel> findByOwnerId(Long ownerId); // Buscar grupos por admin (owner)
}

