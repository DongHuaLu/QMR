package com.game.map.handler{

	import com.game.map.message.ResMonsterRunPositionsMessage;
	import net.Handler;

	public class ResMonsterRunPositionsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterRunPositionsMessage = ResMonsterRunPositionsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}