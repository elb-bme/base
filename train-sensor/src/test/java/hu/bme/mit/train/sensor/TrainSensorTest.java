package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainUser user;
    TrainController controller;
    TrainSensorImpl sensor;


    @Before
    public void setup() {

        user = Mockito.mock(TrainUser.class);
       // user.setAlarmState(false);
        controller=Mockito.mock(TrainController.class);;
        controller.setSpeedLimit(1000);
        sensor=new TrainSensorImpl(controller,user);

    }

    @Test
    public void toFiveHundred() {

        sensor.overrideSpeedLimit(500);
        verify(user,times(1)).setAlarmState(false);
    }
    @Test
    public void toMinusOne() {
        sensor.overrideSpeedLimit(-1);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void toFiveHundredOne() {

        sensor.overrideSpeedLimit(501);
        verify(user,times(1)).setAlarmState(true);

    }

    @Test
    public void fromHundredFiftyToSeventySix() {
        for(int i=0; i<150; i++){
            controller.setJoystickPosition(1);
            controller.followSpeed();
        }

        sensor.overrideSpeedLimit(76);
        verify(user,times(1)).setAlarmState(false);

    }
}
