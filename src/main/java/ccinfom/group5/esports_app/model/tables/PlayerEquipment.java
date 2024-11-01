package ccinfom.group5.esports_app.model.tables;

public class PlayerEquipment {
    private String keyboardBrand;
    private String monitorBrand;
    private String audioWearablesBrand;
    private String mouseBrand;
    private int videoResolution;
    private int DPI;
    private float mouseSensitivity;

    public PlayerEquipment(String keyboardBrand, String monitorBrand, String audioWearablesBrand, String mouseBrand, int videoResolution, int DPI, float mouseSensitivity) {
        this.keyboardBrand = keyboardBrand;
        this.monitorBrand = monitorBrand;
        this.audioWearablesBrand = audioWearablesBrand;
        this.mouseBrand = mouseBrand;
        this.videoResolution = videoResolution;
        this.DPI = DPI;
        this.mouseSensitivity = mouseSensitivity;
    }

    public String getAllDetails() {
        String details;

        details = "\'" + keyboardBrand + "\'" + ", " + 
              "\'" + monitorBrand + "\'" + ", " + 
              "\'" + audioWearablesBrand + "\'" + ", " + 
              "\'" + mouseBrand + "\'" + ", " +
              "\'" + videoResolution + "\'" + ", " +
              DPI + ", " +
              mouseSensitivity;

        return details;
    }

    public String getKeyboardBrand() {
        return keyboardBrand;
    }

    public void setKeyboardBrand(String keyboardBrand) {
        this.keyboardBrand = keyboardBrand;
    }

    public String getMonitorBrand() {
        return monitorBrand;
    }

    public void setMonitorBrand(String monitorBrand) {
        this.monitorBrand = monitorBrand;
    }

    public String getAudioWearablesBrand() {
        return audioWearablesBrand;
    }

    public void setAudioWearablesBrand(String audioWearablesBrand) {
        this.audioWearablesBrand = audioWearablesBrand;
    }

    public String getMouseBrand() {
        return mouseBrand;
    }

    public void setMouseBrand(String mouseBrand) {
        this.mouseBrand = mouseBrand;
    }

    public int getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(int videoResolution) {
        this.videoResolution = videoResolution;
    }

    public int getDPI() {
        return DPI;
    }

    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public float getMouseSensitivity() {
        return mouseSensitivity;
    }

    public void setMouseSensitivity(float mouseSensitivity) {
        this.mouseSensitivity = mouseSensitivity;
    }
}
