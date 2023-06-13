package clientapp.listaobecnosci.Shared.Helpers;

import clientapp.listaobecnosci.Shared.Enums.PresenceStatus;

public class PresenceStatusHelper {
    public static String getPresenceStatus(PresenceStatus presenceStatus) {
        switch (presenceStatus) {
            case PRESENT:
                return "Present";
            case ABSENT:
                return "Absent";
            case JUSTIFIED:
                return "Justified";
            case LATE:
                return "Late";
            default:
                return "Unknown";
        }
    }

    public static PresenceStatus getPresenceStatus(String presenceStatus) {
        switch (presenceStatus.toUpperCase()) {
            case "PRESENT":
                return PresenceStatus.PRESENT;
            case "ABSENT":
                return PresenceStatus.ABSENT;
            case "JUSTIFIED":
                return PresenceStatus.JUSTIFIED;
            case "LATE":
                return PresenceStatus.LATE;
            default:
                return PresenceStatus.UNKNOWN;
        }
    }
}
