package CommandPattern;

public class Simulator {

	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl();//一个遥控器实例
		Light light = new Light();//一个灯实例
		LightOnCommand lightOn = new LightOnCommand(light);
		
		remoteControl.setCommand(lightOn);
		remoteControl.pressBtn();
		remoteControl.pressBack();
	}

}
