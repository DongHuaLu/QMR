package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponErrorMessage;
	import net.Handler;

	public class ResHiddenWeaponErrorHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponErrorMessage = ResHiddenWeaponErrorMessage(this.message);
			//TODO 添加消息处理
		}
	}
}