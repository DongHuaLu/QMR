package com.game.map.handler{

	import com.game.map.message.ResRoundEffectMessage;
	import net.Handler;

	public class ResRoundEffectHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundEffectMessage = ResRoundEffectMessage(this.message);
			//TODO 添加消息处理
		}
	}
}