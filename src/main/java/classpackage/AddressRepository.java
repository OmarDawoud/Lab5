package classpackage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "AddressBook", path = "people")
public interface AddressRepository extends CrudRepository<BuddyInfo, Long> {


    List<BuddyInfo> findByName(@Param("name") String name);

    AddressBook findById(long id);
}