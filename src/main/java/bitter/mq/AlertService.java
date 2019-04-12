package bitter.mq;

import bitter.domain.Bittle;

public interface AlertService {
    void sendBittleAlert(String msg);
    void sendBittleAlertToDefault(String msg);
}
