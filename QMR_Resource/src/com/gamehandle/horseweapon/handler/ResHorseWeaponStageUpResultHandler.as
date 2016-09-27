package com.game.horseweapon.handler{

	import com.game.horseweapon.message.ResHorseWeaponStageUpResultMessage;
	import net.Handler;

	public class ResHorseWeaponStageUpResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHorseWeaponStageUpResultMessage = ResHorseWeaponStageUpResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}