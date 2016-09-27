package com.game.horseweapon.handler{

	import com.game.horseweapon.message.ResHorseWeaponInfoMessage;
	import net.Handler;

	public class ResHorseWeaponInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHorseWeaponInfoMessage = ResHorseWeaponInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}