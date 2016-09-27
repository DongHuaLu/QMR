package com.game.map.handler{

	import com.game.map.message.ResRoundMonsterMessage;
	import net.Handler;

	public class ResRoundMonsterHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundMonsterMessage = ResRoundMonsterMessage(this.message);
			//TODO 添加消息处理
		}
	}
}