package CommandPattern;

/**
 * @author chenhaoyu 一个遥控器
 */
public class RemoteControl {
	Command slot;// 一个插槽 用来放命令

	public RemoteControl() {
		slot = new NoCommand();// 不初始化的话，如果不设置命令就会出异常，一个插槽看不出问题，但如果有很多插槽，又不用全部都用上，那就把用不上的初始化成空命令
	}

	public void setCommand(Command command) {
		this.slot = command;
	}

	public void pressBtn() {
		slot.execute();
	}

	public void pressBack() {
		slot.undo();
	}
}
