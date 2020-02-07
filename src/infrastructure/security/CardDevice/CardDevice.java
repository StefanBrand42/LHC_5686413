package infrastructure.security.CardDevice;

import human_Resource.Employee;
import human_Resource.Person;
import infrastructure.security.IDCard;

public abstract class CardDevice {

    protected IIrisScaenner irisScaenner;
    protected ITouchpad touchpad;
    protected IFingerAbScaenner fingerAbScaenner;



    public void setIrisScaenner(IIrisScaenner irisScaenner) {

        this.irisScaenner = irisScaenner;
    }

    public void setTouchpad(ITouchpad touchpad) {

        this.touchpad = touchpad;
    }

    public void setFingerAbScaenner(IFingerAbScaenner fingerAbScaenner) {
        this.fingerAbScaenner = fingerAbScaenner;
    }

    public IIrisScaenner getIrisScaenner() {

        return irisScaenner;
    }

    public ITouchpad getTouchpad() {

        return touchpad;
    }

    public IFingerAbScaenner getFingerAbScaenner() {
        return fingerAbScaenner;
    }
}
