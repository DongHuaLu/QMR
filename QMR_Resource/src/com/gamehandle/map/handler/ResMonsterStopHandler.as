package com.game.map.handler{

	import com.game.map.message.ResMonsterStopMessage;
	import net.Handler;

	public class ResMonsterStopHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterStopMessage = ResMonsterStopMessage(this.message);
			//TODO 添加消息处理
		}
	}
}