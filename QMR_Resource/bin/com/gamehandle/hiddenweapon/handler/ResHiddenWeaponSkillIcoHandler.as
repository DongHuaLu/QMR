package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponSkillIcoMessage;
	import net.Handler;

	public class ResHiddenWeaponSkillIcoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponSkillIcoMessage = ResHiddenWeaponSkillIcoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}