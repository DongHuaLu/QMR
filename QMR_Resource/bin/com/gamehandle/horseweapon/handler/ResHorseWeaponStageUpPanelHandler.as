package com.game.horseweapon.handler{

	import com.game.horseweapon.message.ResHorseWeaponStageUpPanelMessage;
	import net.Handler;

	public class ResHorseWeaponStageUpPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHorseWeaponStageUpPanelMessage = ResHorseWeaponStageUpPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}