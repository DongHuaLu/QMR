package com.game.map.handler{

	import com.game.map.message.ResRoundEffectDisappearMessage;
	import net.Handler;

	public class ResRoundEffectDisappearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundEffectDisappearMessage = ResRoundEffectDisappearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}