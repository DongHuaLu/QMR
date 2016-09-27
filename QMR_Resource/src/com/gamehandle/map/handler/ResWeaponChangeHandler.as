package com.game.map.handler{

	import com.game.map.message.ResWeaponChangeMessage;
	import net.Handler;

	public class ResWeaponChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWeaponChangeMessage = ResWeaponChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}