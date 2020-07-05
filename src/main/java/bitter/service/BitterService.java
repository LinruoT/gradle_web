package bitter.service;

import bitter.domain.Bitter;

import java.util.List;

/**
 * Services for Bitter
 *
 * todo: add methods for setting/getting relationship of bitter
 * @version        Enter version here..., 19/07/28
 * @author         Enter your name here...
 */
public interface BitterService {
    /**
     * 关注
     * @param bitter 要关注的对象
     * @return 是否成功
     */
    Boolean follow(Bitter bitter, String currentBitterName);

    /**
     * 取消关注
     * @param bitter 要取消关注的对象
     * @return 是否成功
     */
    Boolean unfollow(Bitter bitter, String currentBitterName);

}

