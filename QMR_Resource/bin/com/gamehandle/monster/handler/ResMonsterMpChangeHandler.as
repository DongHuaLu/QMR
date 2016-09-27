package com.game.monster.handler{

	import com.game.monster.message.ResMonsterMpChangeMessage;
	import net.Handler;

	public class ResMonsterMpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterMpChangeMessage = ResMonsterMpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}