package classpackage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "BuddyInfo", path = "buddy")
public interface BuddyRepository extends CrudRepository<BuddyInfo, Long> {


    List<BuddyInfo> findByName(@Param("name") String name);

    BuddyInfo findById(long id);
}