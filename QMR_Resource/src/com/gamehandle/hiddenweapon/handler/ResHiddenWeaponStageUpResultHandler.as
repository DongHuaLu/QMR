package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponStageUpResultMessage;
	import net.Handler;

	public class ResHiddenWeaponStageUpResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponStageUpResultMessage = ResHiddenWeaponStageUpResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}