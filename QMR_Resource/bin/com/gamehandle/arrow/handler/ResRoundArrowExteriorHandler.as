package com.game.arrow.handler{

	import com.game.arrow.message.ResRoundArrowExteriorMessage;
	import net.Handler;

	public class ResRoundArrowExteriorHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundArrowExteriorMessage = ResRoundArrowExteriorMessage(this.message);
			//TODO 添加消息处理
		}
	}
}