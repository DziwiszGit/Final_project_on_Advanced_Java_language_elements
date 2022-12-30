package pl.wszib.edu.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wszib.edu.model.Account;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountDAO extends CrudRepository<Account, UUID> {

}
