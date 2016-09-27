package com.game.monster.handler{

	import com.game.monster.message.ResMonsterMaxSpChangeMessage;
	import net.Handler;

	public class ResMonsterMaxSpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterMaxSpChangeMessage = ResMonsterMaxSpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}