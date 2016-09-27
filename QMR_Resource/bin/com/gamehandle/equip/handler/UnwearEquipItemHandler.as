package com.game.equip.handler{

	import com.game.equip.message.UnwearEquipItemMessage;
	import net.Handler;

	public class UnwearEquipItemHandler extends Handler {
	
		public override function action(): void{
			var msg: UnwearEquipItemMessage = UnwearEquipItemMessage(this.message);
			//TODO 添加消息处理
		}
	}
}