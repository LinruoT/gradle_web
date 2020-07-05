package bitter.service;

import bitter.data.BitterRepository;
import bitter.domain.Bitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Services for Bitter
 *
 * todo: add methods implements for getting relationship of bitter
 */
@Service
public class SecuredBitterService implements BitterService {
    private BitterRepository bitterRepository;

    @Autowired
    public SecuredBitterService(BitterRepository bitterRepository) {
        this.bitterRepository = bitterRepository;
    }


    @Override
    public Boolean follow(Bitter bitter, String currentBitterName) {
        //get current bitter
        Bitter currentBitter = bitterRepository.findByUsername(currentBitterName);
        if (bitterRepository.follow(bitter, currentBitter)) {
//            currentBitter.setFollowCount = +1;
//            bitter.setFansCount = +1;
            bitterRepository.save(currentBitter);
            bitterRepository.save(bitter);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean unfollow(Bitter bitter, String currentBitterName) {
        //get current bitter
        Bitter currentBitter = bitterRepository.findByUsername(currentBitterName);
        return bitterRepository.unfollow(bitter, currentBitter);
    }
}
