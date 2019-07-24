package team.gif.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {

    private static OI instance;

    public XboxController driver = new XboxController(0);

    private OI() {

    }

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }


}
