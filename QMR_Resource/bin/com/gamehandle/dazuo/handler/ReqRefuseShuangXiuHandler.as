package com.game.dazuo.handler{

	import com.game.dazuo.message.ReqRefuseShuangXiuMessage;
	import net.Handler;

	public class ReqRefuseShuangXiuHandler extends Handler {
	
		public override function action(): void{
			var msg: ReqRefuseShuangXiuMessage = ReqRefuseShuangXiuMessage(this.message);
			//TODO 添加消息处理
		}
	}
}