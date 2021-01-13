package team.gif.robot;

public abstract class RobotMap {

    // Drive Motors
    public static final int DRIVE_LEFT_ONE = 2;//0
    public static final int DRIVE_LEFT_TWO = 3; //1
    public static final int DRIVE_RIGHT_ONE = 0; //2
    public static final int DRIVE_RIGHT_TWO = 1; //3

    // Other Motors
    public static final int INTAKE = 4; //4
    public static final int STAGE_TWO = 5; //5
    public static final int STAGE_THREE = 6; //6
    public static final int STAGE_FOUR = 7; //7
    public static final int STAGE_FIVE = 8; //8
    public static final int FLYWHEEL = 9; //9
    public static final int HANGER = 11;
    public static final int COLOR_WHEEL = 12;

    // Solenoids
    public static final int INTAKE_SOLENOID_ZERO = 0;
    public static final int INTAKE_SOLENOID_ONE = 1;
    public static final int INTAKE_SOLENOID_TWO = 2;
    public static final int SOLENOID_HANGER = 3;

    // Controllers
    public static final int DRIVER_CONTROLLER_ID = 0;
    public static final int AUX_CONTROLLER_ID = 1;

    //pigeon
    public static final int PIGEON = 2; // ony used on practice bot

}
