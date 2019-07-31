package bitter.service;

import org.springframework.stereotype.Component;

import bitter.domain.Bittle;

/**
 * Interface description
 *
 *
 * @version        Enter version here..., 19/07/29
 * @author         Enter your name here...
 */
public interface BittleService {

    /**
     * 增加bittle
     *
     *
     * @param bittle
     *
     * @return
     */
    Bittle addBittle(Bittle bittle);

    /**
     * 删除bittle
     *
     *
     * @param bittle 将被删除的bittle
     * @param bitterName 当前用户username
     *
     * @return
     */
    boolean forceDeleteBittle(Bittle bittle, String bitterName);
}


//~ Formatted by Jindent --- http://www.jindent.com
