package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResOthersHiddenWeaponInfoMessage;
	import net.Handler;

	public class ResOthersHiddenWeaponInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOthersHiddenWeaponInfoMessage = ResOthersHiddenWeaponInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}