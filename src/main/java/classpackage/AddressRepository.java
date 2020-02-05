package classpackage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "AddressBook", path = "people")
public interface AddressRepository extends CrudRepository<AddressBook, Long> {

    AddressBook findById(long id);


}