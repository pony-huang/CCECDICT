package org.github.ponking66.ccecdit.util;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

/**
 * @author pony
 * @date 2023/7/15
 */
public class NotificationUtil {

    public static void notifyWarning(Project project, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("CCECDICT Notification")
                .createNotification(content, NotificationType.WARNING)
                .notify(project);
    }

    public static void notifyError(Project project, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("CCECDICT Notification")
                .createNotification(content, NotificationType.ERROR)
                .notify(project);
    }

    public static void notifyInformation(Project project, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("CCECDICT Notification")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }
}
