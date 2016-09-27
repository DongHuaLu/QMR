package com.game.map.handler{

	import com.game.map.message.ResRoundMonsterDisappearMessage;
	import net.Handler;

	public class ResRoundMonsterDisappearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundMonsterDisappearMessage = ResRoundMonsterDisappearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}