package com.game.marriage.handler{

	import com.game.marriage.message.ResSpouseSwitchmapToClientMessage;
	import net.Handler;

	public class ResSpouseSwitchmapToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSpouseSwitchmapToClientMessage = ResSpouseSwitchmapToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}