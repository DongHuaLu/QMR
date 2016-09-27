package com.game.equip.handler{

	import com.game.equip.message.WearEquipItemMessage;
	import net.Handler;

	public class WearEquipItemHandler extends Handler {
	
		public override function action(): void{
			var msg: WearEquipItemMessage = WearEquipItemMessage(this.message);
			//TODO 添加消息处理
		}
	}
}