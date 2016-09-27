package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponInfoMessage;
	import net.Handler;

	public class ResHiddenWeaponInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponInfoMessage = ResHiddenWeaponInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}