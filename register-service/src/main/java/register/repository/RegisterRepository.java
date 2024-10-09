package register.repository;

import register.model.RegisterModel;

public interface RegisterRepository {

    // usar o JPA/Hibernate (JpaRepository ou CrudRepository) que já abstrai o CRUD
    void crete();
    void read();
    void update();
    void delete();



}
