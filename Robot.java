package org.usfirst.frc.team5510.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements PIDOutput{
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser<String> chooser = new SendableChooser<>();
    
    boolean forward = true;
    boolean backwards = false;
    
    RobotDrive hamiRobot;
    
    //Victors are the speed controllers, find the ports on the roboRIO
    Victor right1;
    Victor right2;
    Victor left1;
    Victor left2;
    
    Joystick xboxController;


    public Robot() {
        hamiRobot.setExpiration(0.1);
        
             xboxController = new JoyStick(0);
             right1 = new Victor (1); //port 1
        right2 = new Victor (2); //port 2
        left1 = new Victor (3); //port 3
        left2 = new Victor (4); //port 4
        hamiRobot = new RobotDrive(right1, right2, left1, left2);
        }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        autoSelect = chooser.getSelected();
        autoSelect = SmartDashboard.getString("Auto Selector",
        defaultAuto);
        System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        switch (autoSelect) {
        case customAuto:
            
// Put custom auto code here
            break;
        case defaultAuto:
        default:
            // We’ll code auto stuff later
// Put default auto code here
            break;
        }
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        setReverse();
        if (forward) {
            hamiRobot.tankDrive(-xboxController.getRawAxis(1) * 0.9, -xboxController.getRawAxis(5) * 0.9);
        }
        else {
            hamiRobot.tankDrive(xboxController.getRawAxis(5) * 0.9, xboxController.getRawAxis(1) * 0.9, true);
        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void pidWrite(double output){
        
    }
    
    private void setReverse(){        //sets robot in reverse mode
               if(xboxController.getRawButton(4)){ // button 4 is left bumper on controller
                   if(!backwards){
                       forward = !forward;
                       backwards = true;}
               }
               else {
                   backwards = false;
               }
        }
    

}
