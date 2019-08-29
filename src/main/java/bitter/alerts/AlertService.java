package bitter.alerts;

import bitter.domain.Bitter;
import bitter.domain.Bittle;

public interface AlertService {
    void sendBittleAlert(Bittle bittle);
    void sendBitterAlert(Bitter bitter);
    void sendTestAlert(int count);
}
