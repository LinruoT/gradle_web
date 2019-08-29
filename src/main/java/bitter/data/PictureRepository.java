package bitter.data;

import bitter.domain.Bitter;
import bitter.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture,Long> {
    List<Picture> findByBitter(Bitter bitter);
}
