package com.game.monster.handler{

	import com.game.monster.message.ResMonsterSpChangeMessage;
	import net.Handler;

	public class ResMonsterSpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterSpChangeMessage = ResMonsterSpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}