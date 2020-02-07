package infrastructure.security.CardDevice;

import human_Resource.Person;
import infrastructure.security.IDCard;

public abstract interface ICardDevice {
    void setIrisScaenner(IIrisScaenner irisScaenner);
    void setTouchpad(ITouchpad touchpad);
    IIrisScaenner getIrisScaenner();
    ITouchpad getTouchpad();
    IFingerAbScaenner getFingerAbScaenner();
    void setFingerAbScaenner(IFingerAbScaenner fingerAbScaenner);
    void removeIDCard();





}
