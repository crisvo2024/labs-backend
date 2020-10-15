package co.edu.unal.software_engineering.labs.repository;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Integer>{

    @Query("select a from Association a where a.userRole.userRolePK.user=:user")
    List<Association> findAssociation(@Param("user") User user);
}
