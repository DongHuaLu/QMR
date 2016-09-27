package com.game.monster.handler{

	import com.game.monster.message.ResMonsterHpChangeMessage;
	import net.Handler;

	public class ResMonsterHpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterHpChangeMessage = ResMonsterHpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}