package bitter.data;

import bitter.domain.Bitter;

//用户库接口
public interface BitterRepository {
    Bitter save(Bitter bitter);
    Bitter findByUsername(String username);
}
