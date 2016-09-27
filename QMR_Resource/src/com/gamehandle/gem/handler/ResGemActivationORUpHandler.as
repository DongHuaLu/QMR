package com.game.gem.handler{

	import com.game.gem.message.ResGemActivationORUpMessage;
	import net.Handler;

	public class ResGemActivationORUpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGemActivationORUpMessage = ResGemActivationORUpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}