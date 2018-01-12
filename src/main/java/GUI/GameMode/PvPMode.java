package GUI.GameMode;

public class PvPMode implements GameMode {
    Mode gameType = Mode.PVP;
    String ipA; // A - gracz, który wcisnal klawisz "Host"
    String ipB; // B - gracz, który wcisnal klawisz "Join"
    PlayerStatus statusA = PlayerStatus.UNDEFINED;
    PlayerStatus statusB = PlayerStatus.UNDEFINED;

    public PvPMode() {
    }

    @Override
    public Mode getMode() {
        return gameType;
    }

    public void setIpA(String ipA) {
        this.ipA = ipA;
    }

    public void setIpB(String ipB) {
        this.ipB = ipB;
    }

    public String getIPA() {
        return ipA;
    }

    public String getIPB() {
        return ipB;
    }

    public void setStatusForPlayerAToReady() {
        this.statusA = PlayerStatus.READY;
    }

    public void setStatusForPlayerBToReady() {
        this.statusB = PlayerStatus.READY;
    }

    public boolean areTwoPlayersReady() {
        return statusA == PlayerStatus.READY && statusB == PlayerStatus.READY;
    }
}
