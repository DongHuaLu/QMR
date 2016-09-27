package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResWearHiddenWeaponStateMessage;
	import net.Handler;

	public class ResWearHiddenWeaponStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWearHiddenWeaponStateMessage = ResWearHiddenWeaponStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}