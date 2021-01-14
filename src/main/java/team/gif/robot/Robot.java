
package team.gif.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import team.gif.lib.autoMode;
import team.gif.robot.commands.autos.*;
import team.gif.robot.commands.drivetrain.Drive;
import team.gif.robot.subsystems.Drivetrain;
import team.gif.robot.subsystems.drivers.Limelight;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand = null;

  private Command driveCommand = null;

  private SendableChooser<autoMode> autoModeChooser = new SendableChooser<>();

  private autoMode chosenAuto;


  public static Limelight limelight;

  public static ShuffleboardTab autoTab = Shuffleboard.getTab("PreMatch");
  private NetworkTableEntry allianceEntry = autoTab.add("Alliance","Startup")
                                                    .withPosition(3,0)
                                                    .withSize(1,1)
                                                    .getEntry();

  public static OI oi;
  private Drivetrain drivetrain = null;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    System.out.println("robot init");
    tabsetup();
    // autonomous chooser on the dashboard.

    driveCommand = new Drive(Drivetrain.getInstance());
    drivetrain = Drivetrain.getInstance();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    chosenAuto = autoModeChooser.getSelected();

    CommandScheduler.getInstance().run();

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    System.out.println("autonomous init start");

    drivetrain.resetEncoders();
    drivetrain.resetPose();
    System.out.println("Auto: Sensors Reset");

    updateauto();
      System.out.println("Auto: auto selection updated");

    m_autonomousCommand.schedule();

    System.out.println("autonomous init end");

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    System.out.println("teleop init");

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    oi = new OI();
    driveCommand.schedule();
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic(){
  }

  public void tabsetup(){

    autoTab = Shuffleboard.getTab("PreMatch");

    autoModeChooser.setDefaultOption("Mobility", autoMode.MOBILITY);

    autoTab.add("Auto Select",autoModeChooser)
            .withWidget(BuiltInWidgets.kComboBoxChooser)
            .withPosition(1,0)
            .withSize(2,1);

  }

  public void updateauto(){

    if(chosenAuto == autoMode.MOBILITY){
        m_autonomousCommand = new Mobility();
    } else if(chosenAuto ==null) {
        System.out.println("Autonomous selection is null. Robot will do nothing in auto :(");
    }
  }

}