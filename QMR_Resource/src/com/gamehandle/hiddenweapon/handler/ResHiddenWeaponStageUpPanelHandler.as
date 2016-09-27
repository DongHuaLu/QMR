package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponStageUpPanelMessage;
	import net.Handler;

	public class ResHiddenWeaponStageUpPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponStageUpPanelMessage = ResHiddenWeaponStageUpPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}