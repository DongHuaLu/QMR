package com.game.horseweapon.handler{

	import com.game.horseweapon.message.ResOthersHorseWeaponInfoMessage;
	import net.Handler;

	public class ResOthersHorseWeaponInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOthersHorseWeaponInfoMessage = ResOthersHorseWeaponInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}