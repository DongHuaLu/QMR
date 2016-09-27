package com.game.horseweapon.handler{

	import com.game.horseweapon.message.ResHorseWeaponErrorMessage;
	import net.Handler;

	public class ResHorseWeaponErrorHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHorseWeaponErrorMessage = ResHorseWeaponErrorMessage(this.message);
			//TODO 添加消息处理
		}
	}
}